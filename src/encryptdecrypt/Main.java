package encryptdecrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> config = new HashMap<>();

        config.put("-mode", "enc");
        config.put("-key", "0");
        config.put("-data", null);
        config.put("-out", null);
        config.put("-in", null);
        config.put("-alg", "shift");

        // Read configuration from args
        for (int i = 0; i < args.length; i += 2) {
            if (config.containsKey(args[i])) {
                if (args[i + 1].startsWith("-")) {
                    System.out.printf("Error! %s value is not present.\n", args[i]);
                    System.exit(0);
                }
                config.put(args[i], args[i + 1]);
            }
        }

        // Use data from configuration or file
        String data = null;
        if (config.get("-data") == null && config.get("-in") == null) {
            data = "";
        } else if (config.get("-data") != null){
            data = config.get("-data");
        } else {
            File in = new File(config.get("-in"));
            if (in.exists()) {
                try(FileInputStream fis = new FileInputStream(in)) {
                    data = new String(fis.readAllBytes());
                } catch (IOException e) {
                    System.out.println("Error! " + e.getMessage());
                    System.exit(0);
                }
            } else {
                System.out.println("Error! input file doesn't exist.\n");
                System.exit(0);
            }
        }

        Cipher cipher = new Cipher();
        String result;
        int key = Integer.parseInt(config.get("-key"));

        // Use the requested cipher algorithm
        if ("unicode".equals(config.get("-alg"))) {
            cipher.setCipherType(new UnicodeCipher());
        } else {
            cipher.setCipherType(new CaesarCipher());
        }

        // Set cipher mode to encode or decode
        if ("enc".equals(config.get("-mode"))) {
            result = cipher.encode(data, key);
        } else {
            result = cipher.decode(data, key);
        }

        // Print results to a file or standard output
        if (config.get("-out") != null) {
            try(FileOutputStream fileOutputStream = new FileOutputStream(config.get("-out"))) {
                fileOutputStream.write(result.getBytes());
                fileOutputStream.flush();
            } catch (IOException e) {
                System.out.println("Error! " + e.getMessage());
            }
        } else {
            System.out.println(result);
        }
    }
}
