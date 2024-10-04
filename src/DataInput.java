public class DataInput {
    private Long memSize;
    private int words;
    private int lines;
    private int lanes;
    private int[] address;

    int memBitSize;
    int lineBytes;
    int sets;

    int blockBitSize;
    int setSizes;
    int tagBits;
    int cacheMiss;
    int cacheHit;


    public DataInput(Long memSize,int words,int lines,int lanes,int[] address){
        this.memSize = memSize;
        this.words = words;
        this.lines = lines;
        this.lanes = lanes;
        this.address = address;

        findAllValues();
    }

    private void findAllValues(){
        this.memBitSize = getBit(getAddress()[0]);
        this.lineBytes = 4 * getWords();
        this.sets = getLines() / getLanes();
        this.blockBitSize = getBit(lineBytes);
        this.setSizes = getBit(sets);
        this.tagBits = 0;
        this.cacheMiss = 0;
        this.cacheHit = 0;
    }

    
    public int getBit(int value){
        if (value == 0)
            return 1;
        
        return (int)(Math.floor(Math.log(value) / Math.log(2))) + 1;
    }
    public String printData() {
        String data = "" + getMemSize() + "\n" +  getWords() +"\n"+ getLines() +"\n"+  getLanes() +"\n" + adressToString();
        return data;
    }

    public String adressToString(){
        String addressString = "";
        for(int i = 0; i < this.address.length; i++)
            addressString += this.address[i] +" ";
        return addressString;
    }

    public int getMemBitSize() {
        return memBitSize;
    }

    public int getLineBytes() {
        return lineBytes;
    }

    public int getSets() {
        return sets;
    }

    public int getBlockBitSize() {
        return blockBitSize;
    }

    public int getSetSizes() {
        return setSizes;
    }

    public int getTagBits() {
        return tagBits;
    }

    public int getCacheMiss() {
        return cacheMiss;
    }

    public int getCacheHit() {
        return cacheHit;
    }

    public Long getMemSize() {
        return memSize;
    }

    public int getWords() {
        return words;
    }

    public int getLines() {
        return lines;
    }

    public int getLanes() {
        return lanes;
    }

    public int[] getAddress() {
        return address;
    }
}
