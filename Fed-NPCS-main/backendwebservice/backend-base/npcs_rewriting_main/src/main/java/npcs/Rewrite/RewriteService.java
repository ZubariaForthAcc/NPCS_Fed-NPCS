package npcs.Rewrite;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class RewriteService {
	public static String runJar(String reification, String type, String query) {
		//System.out.println(query);
		//System.out.println(type);
		//System.out.println(reification);
		String results = "";
        try {
          //  String jarFilePath = "src\\main\\java\\jarfiles\\serviceSparqlStarUpdatedBytes.jar";
        	 String jarFilePath = "src\\main\\java\\jarfiles\\ServiceSparqlStarUpdatedBytesNewFinal.jar";
        	List<String> command = new ArrayList<>();
            command.add("java");
            command.add("-jar");
            command.add(jarFilePath);
            command.add(reification);
            command.add(type);
            command.add(query);

            ProcessBuilder processBuilder = new ProcessBuilder(command);

            
            long startTime = System.currentTimeMillis();
            // Start the process
            Process process = processBuilder.start();
            // Read the raw output from the process
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder outputBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                outputBuilder.append(line);
              //  System.out.println(line);
            }
            String output = outputBuilder.toString().trim();
          //  System.out.println("Output from Service JAR file: " + output);

            // Parse the byte array from the output (assuming it's in the format [91, 66, ...])
            byte[] byteArray = parseByteArray(output);
          //  System.out.println("Byte Array: " + byteArrayToString(byteArray));

            // Convert the byte array back to the original string
            String originalString = new String(byteArray, StandardCharsets.UTF_8);
            System.out.println("Rewritten Query: " + originalString);

            // End timing and calculate elapsed time
            long endTime = System.currentTimeMillis();
            long timeTaken = endTime - startTime;
     //       System.out.println("Time taken by rewriting: " + timeTaken + " milliseconds");
            
            
            /*
            long startTime = System.currentTimeMillis();
            // Start the process
            Process process = processBuilder.start();
            // Redirect output and error streams
            processBuilder.redirectErrorStream(true);
            // Read the raw output from the process
            InputStream inputStream = process.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            System.out.println("Raw Byte Array Length: " + byteArray.length);  // Check length of byte array
            System.out.println("Raw Byte Array Content: " + Arrays.toString(byteArray));  // Print byte array content

            String original_string = new String(byteArray, StandardCharsets.UTF_8);
            System.out.println("Original String: " + original_string);

            */
            
            
            
            /*
            
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            System.out.println("Byte Array:"+byteArray);
            String original_string=new String(byteArray,StandardCharsets.UTF_8);
            System.out.println("original_string Array:"+original_string);
           */
            // Convert the byte array to a string
           /*
            String output = new String(byteArray, StandardCharsets.UTF_8);

            // Log the byte array and the output string
            System.out.println("Byte Array: " + Arrays.toString(byteArray)); // Logs the actual byte array
            System.out.println("Output from Service JAR file: " + output.trim()); // Logs the string representation

            // Use the string output directly as the parsed string
            String parsedString = output.trim();
            System.out.println("Parsed string: " + parsedString);

            // End timing and calculate elapsed time
            long endTime = System.currentTimeMillis();
            long timeTaken = endTime - startTime;
            System.out.println("Time taken by rewriting: " + timeTaken + " milliseconds");
            */
            // Wait for the process to complete
            int exitCode = process.waitFor();
            
            
            
            /*
            // Start the process
            Process process = processBuilder.start();

            // Read the output of the process
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder outputBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                outputBuilder.append(line);
            }
            String output = outputBuilder.toString();
            System.out.println("Output from JAR file: " +  Arrays.toString(outputBuilder));

            // Parse the output as a byte array
            byte[] byteArray = parseByteArray(output);
            String character2 = new String(byteArray, StandardCharsets.UTF_8);
            results=character2;


            // Wait for the process to complete
            int exitCode = process.waitFor();
*/
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return results;
    }
	/*private static byte[] parseByteArray(String output) {
	   // System.out.println("Input string: " + output); // Print the input string
	    
	    int startIndex = output.indexOf('[');
	    int endIndex = output.lastIndexOf(']');
	    
	    // Check if '[' and ']' characters are found
	    if (startIndex == -1 || endIndex == -1) {
	        // Handle error, throw exception or return null depending on your requirements
	        throw new IllegalArgumentException("Invalid format: '[' or ']' not found in the string");
	    }
	    
	    String byteString = output.substring(startIndex + 1, endIndex);
	    String[] byteStrings = byteString.split(", ");
	    byte[] byteArray = new byte[byteStrings.length];
	    for (int i = 0; i < byteStrings.length; i++) {
	        byteArray[i] = Byte.parseByte(byteStrings[i].trim());
	    }
	    return byteArray;
	}
	*/
	  private static byte[] parseByteArray(String output) {
	        int startIndex = output.indexOf('[');
	        int endIndex = output.lastIndexOf(']');
	        String byteString = output.substring(startIndex + 1, endIndex);
	        String[] byteStrings = byteString.split(", ");
	        byte[] byteArray = new byte[byteStrings.length];
	        for (int i = 0; i < byteStrings.length; i++) {
	            byteArray[i] = Byte.parseByte(byteStrings[i].trim());
	        }
	        return byteArray;
	    }/*
	  public static byte[] parseByteArray(String input) {
	        input = input.replaceAll("\\[|\\]|\\s", ""); // Remove brackets and spaces
	        String[] byteValues = input.split(",");
	        byte[] byteArray = new byte[byteValues.length];
	        for (int i = 0; i < byteValues.length; i++) {
	            byteArray[i] = Byte.parseByte(byteValues[i]);
	        }
	        return byteArray;
	    }

	    /**
	     * Converts a byte array to a human-readable string representation.
	     */
	    public static String byteArrayToString(byte[] byteArray) {
	        StringBuilder sb = new StringBuilder("[");
	        for (int i = 0; i < byteArray.length; i++) {
	            sb.append(byteArray[i]);
	            if (i < byteArray.length - 1) {
	                sb.append(", ");
	            }
	        }
	        sb.append("]");
	        return sb.toString();
	    }



}
