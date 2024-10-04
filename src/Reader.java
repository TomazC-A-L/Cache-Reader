import java.io.*;

public class Reader {

    public static void ler (String file) throws IOException {
        BufferedReader read = new BufferedReader(new FileReader(removerLinhaVazia(file)));
        String linha;

        Long memSizeLong = 0L;
        int[] memoryInfos = new int[3];
        linha = read.readLine();
        Long aux = Long.parseLong(linha);
        
        if(linha != null)
            if(aux > 4294967296L){
                System.out.println("Este endereço de memoria é muito grande!!\nEncerrando leitura deste arquivo...");
                read.close();
                return;
            }
            else 
                memSizeLong = aux;
            
        

        for(int i = 0; i < 3;){
            linha = read.readLine();
            if(!linha.isEmpty()){
                memoryInfos[i] = Integer.parseInt(linha);
                i++;
            }
        }

        if((linha = read.readLine()) != null)
            if(
            (memSizeLong >= 1 && memSizeLong <= 4294967296L) &&
            (memoryInfos[0] >= 1 && memoryInfos[0] <= 65536) &&
            (memoryInfos[1] >= 1 && memoryInfos[1] <= 65536) &&
            (memoryInfos[2] >= 1 && memoryInfos[2] <= 1024)){

                String[] temp = linha.split(" ");

                if(temp.length >= 1 && temp.length <= 2048 ) {
                    int[] adrs = new int[temp.length]; // endereços de memoria
                    for(int i = 0; i < temp.length; i++){
                        int verify = Integer.parseInt(temp[i]);
                        if(verify >= 0 && (verify <= (memoryInfos[0]-1) || verify <= memSizeLong))
                        // criar o cache hit e cache miss!!!
                        /* criar lista de hits e verificar */
                            adrs[i] = Integer.parseInt(temp[i]);
                        else{
                            adrs[i] = -1;
                            System.out.println("o "+ i+ "º endereço de memória é grande ou pequeno demais, ele será disposto como '-1'");
                        }
                    }

                    DataInput memory = new DataInput(memSizeLong, memoryInfos[0], memoryInfos[1], memoryInfos[2], adrs);
                    
                    System.out.println(MapingType.CheckMaping(memory));
                    read.close();
                }
                else 
                    System.out.println("a quantidades de endereços informada excede 2048, finalizando leitura de arquivo...");
            }
            else 
                System.out.println("nenhum endereço foi informado");
        
        else
            System.out.println("Os dados informados no arquivo fogem dos padrões impostos para se estruturar uma memória!");
    }

    public static String removerLinhaVazia(String file) {
        String caminhoArquivoOriginal = file;
        String aux = file.split("\\\\")[2];

        String novoDiretorio = "src\\results\\";
        String caminhoArquivoNovo = novoDiretorio + aux + "-result.txt";

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivoOriginal));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivoNovo))) {

            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (!linha.trim().isEmpty()) { // Ignora linhas vazias
                    escritor.write(linha);
                    escritor.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return caminhoArquivoNovo;
    }
}
