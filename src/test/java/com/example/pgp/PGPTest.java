package com.example.pgp;

import org.junit.Test;

/**
 * Created by manish on 28/03/17.
 */
public class PGPTest {
    private static final String PASSPHRASE = "test";

    private static final String DE_INPUT = "/Users/manish/IdeaProjects/java-pgp/src/main/resources/test1.pgp";
    private static final String DE_OUTPUT = "/Users/manish/IdeaProjects/java-pgp/src/main/resources/test2.txt";
    private static final String DE_KEY_FILE = "/Users/manish/IdeaProjects/java-pgp/src/main/resources/pgp_private_key.txt";

    private static final String E_INPUT = "/Users/manish/IdeaProjects/java-pgp/src/main/resources/test.txt";
    private static final String E_OUTPUT = "/Users/manish/IdeaProjects/java-pgp/src/main/resources/test1.pgp";
    private static final String E_KEY_FILE = "/Users/manish/IdeaProjects/java-pgp/src/main/resources/pgp_public_key.txt";

    @Test
    public void decrypt() throws Exception {
        PGPFileProcessor p = new PGPFileProcessor(PASSPHRASE,E_KEY_FILE,DE_KEY_FILE,DE_INPUT,DE_OUTPUT);
        System.out.println(p.decrypt());
    }

    @Test
    public void encrypt() throws Exception {
        PGPFileProcessor p = new PGPFileProcessor(PASSPHRASE,E_KEY_FILE,DE_KEY_FILE,E_INPUT,E_OUTPUT);
        System.out.println(p.encrypt());
    }
}
