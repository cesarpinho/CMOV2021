const crypto = require('crypto')
const forge = require('node-forge')
const db = require('./models/index.js')

/**
 * Determines whether the name variable corresponds
 * to a valid persons' name
 * 
 * @param {string} name Customer name
 * 
 * @returns {boolean} True if valid, false otherwise
 */
exports.name = function(name) {
    const re = /^[a-zA-ZÀ-ú]+((['. -][a-zA-ZÀ-ú ])?[a-zA-ZÀ-ú ]*)*$/
    return re.test(name)
}
  
/**
 * Determines whether the nickname variable corresponds
 * to a valid nickname
 * 
 * @param {string} nickname Customer nickname
 * 
 * @returns {boolean} True if valid, false otherwise
 */
exports.nickname = function (nickname) {
    const re = /^(?=.{4,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$/
    return re.test(nickname)
}

/**
 * Determines whether the password variable corresponds
 * to a valid password
 * 
 * @param {string} password Customer password
 * 
 * @returns {boolean} True if valid, false otherwise
 */
exports.password = function(password) {
    const re = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{4,}$/
    return re.test(password)
}

/**
 * Determines whether the nif/cif variable corresponds
 * to a valid nif/cif
 * 
 * @param {string} nif Customer nif
 * 
 * @returns {boolean} True if valid, false otherwise
 */
exports.nif = function (nif) {
    const re = /([a-z]|[A-Z]|[0-9])[0-9]{7}([a-z]|[A-Z]|[0-9])/
    return re.test(nif)
}

/**
 * Determines whether the card variable corresponds
 * to a valid card
 * 
 * @param {string} card Customer card
 * 
 * @returns {boolean} True if valid, false otherwise
 */
exports.card = function (card) {
    const re = /^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\d{3})\d{11})$/
    return re.test(card)
}

/**
 * Determines whether the request body of the 
 * register endpoint is valid or not.
 * 
 * @param {object} reqBody Request body
 * 
 * @returns {object} Contains the status of the body validation. 
 * True if everything is ok, false otherwise. Also contains a brief 
 * description about the status.
 */
exports.endpointRegisterBody = function(reqBody) {
    if(!this.name(reqBody.name))
        return {status: false, description: "Invalid customer name: <" + reqBody.name + ">"}

    if(!this.card(reqBody.card))
        return {status: false, description: "Invalid customer card: <" + reqBody.card + ">"}

    if(!this.nif(reqBody.nif))
        return {status: false, description: "Invalid customer nif: <" + reqBody.nif + ">"}

    if(!this.nickname(reqBody.nickname))
        return {status: false, description: "Invalid customer nickname: <" + reqBody.nickname + ">"}

    if(!this.password(reqBody.password))
        return {status: false, description: "Invalid customer password: <" + reqBody.password + ">"}

    return {status: true, description: ""}
}

/**
 * @description Checks the validity of certain signature for a certain user.
 * 
 * @param {object} customer Customer object containing the customer information
 * @param {string} signedData Signature received by the server
 * 
 * @returns {boolean} True if the signature is valid, false otherwise.
 */
exports.validSignature = async function(customer, data, signedData) {
    // Validation result
    let result = false

    // Get the certificates for customer
    const certificates = await db.Certificate.findAll({where: {id_customer: customer.id}})

    certificates.forEach(elem => {
        try {
            let asn1 = forge.asn1.fromDer(forge.util.decode64(elem.certificate))    
            
            const cert = forge.pki.certificateFromAsn1(asn1)
            
            let verifier = crypto.createVerify('sha256WithRSAEncryption')
            verifier.update(Buffer.from(data))
            
            result = verifier.verify(forge.pki.publicKeyToPem(cert.publicKey), signedData, 'base64')
            
            if(result)
                return true
        } catch (_) {}
    })
    return result
}