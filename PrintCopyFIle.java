Void PrintFile(String fileName)
{
    String line = null;
    try {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while((line = bufferedReader.readLine()) != null) {
           System.out.println(line);
    } 
       bufferedReader.close();
    }
    catch (FileNotFoundException e) {
         e.printStackTrace();
    }
    catch (IOException e) {
         e.printStackTrace();
    }
}


void CopyFile(String inputFile, String outputFile) 
{
  final int BUFFER_SIZE = 4096;
  int readLen;
  try {
      InputStream inputStream = new FileInputStream(inputFile);
      OutputStream outputStream = new FileOutputStream(outputFile);
      byte[] buffer = new byte[BUFFER_SIZE];
    
      while ((readLen = inputStream.read(buffer)) != -1) 
      {
        outputStream.write(buffer, 0, readLen);
      }
      inputStream.close();
      outputStream.close();
  } 
  catch (FileNotFoundException e) {
  e.printStackTrace();
  }
  catch (IOException ex) {
  ex.printStackTrace();
  }
}
