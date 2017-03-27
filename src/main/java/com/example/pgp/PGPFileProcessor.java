package com.example.pgp;

import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPSecretKey;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PGPFileProcessor {

    public PGPFileProcessor(String passphrase, String publicKeyFileName, String secretKeyFileName, String inputFileName,
                            String outputFileName, boolean asciiArmored, boolean integrityCheck) {
        this.passphrase = passphrase;
        this.publicKeyFileName = publicKeyFileName;
        this.secretKeyFileName = secretKeyFileName;
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.asciiArmored = asciiArmored;
        this.integrityCheck = integrityCheck;
    }

    public PGPFileProcessor(String passphrase, String publicKeyFileName, String secretKeyFileName, String inputFileName,
                            String outputFileName) {
        this.passphrase = passphrase;
        this.publicKeyFileName = publicKeyFileName;
        this.secretKeyFileName = secretKeyFileName;
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    private String passphrase;
    private String publicKeyFileName;
    private String secretKeyFileName;
    private String inputFileName;
    private String outputFileName;
    private boolean asciiArmored = false;
    private boolean integrityCheck = true;

    public String getPassphrase() {
        return passphrase;
    }

    public String getPublicKeyFileName() {
        return publicKeyFileName;
    }

    public String getSecretKeyFileName() {
        return secretKeyFileName;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public boolean isAsciiArmored() {
        return asciiArmored;
    }

    public boolean isIntegrityCheck() {
        return integrityCheck;
    }

    public boolean encrypt() throws Exception {
        FileInputStream keyIn = new FileInputStream(publicKeyFileName);
        FileOutputStream out = new FileOutputStream(outputFileName);
        PGPUtils.encryptFile(out, inputFileName, PGPUtils.readPublicKey(keyIn), asciiArmored, integrityCheck);
        out.close();
        keyIn.close();
        return true;
    }

    public boolean signEncrypt() throws Exception {
        FileOutputStream out = new FileOutputStream(outputFileName);
        FileInputStream publicKeyIn = new FileInputStream(publicKeyFileName);
        FileInputStream secretKeyIn = new FileInputStream(secretKeyFileName);

        PGPPublicKey publicKey = PGPUtils.readPublicKey(publicKeyIn);
        PGPSecretKey secretKey = PGPUtils.readSecretKey(secretKeyIn);

        PGPUtils.signEncryptFile(
                out,
                this.getInputFileName(),
                publicKey,
                secretKey,
                this.getPassphrase(),
                this.isAsciiArmored(),
                this.isIntegrityCheck() );

        out.close();
        publicKeyIn.close();
        secretKeyIn.close();

        return true;
    }

    public boolean decrypt() throws Exception {
        FileInputStream in = new FileInputStream(inputFileName);
        FileInputStream keyIn = new FileInputStream(secretKeyFileName);
        FileOutputStream out = new FileOutputStream(outputFileName);
        PGPUtils.decryptFile(in, out, keyIn, passphrase.toCharArray());
        in.close();
        out.close();
        keyIn.close();
        return true;
    }

}
