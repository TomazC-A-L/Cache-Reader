import java.util.ArrayList;

public class MapingType {

    public static String CheckMaping(DataInput memory) {

        // direto
        if(memory.getSets() == memory.getLines()){
            int lineBits = memory.getBit(memory.getLines());
            memory.tagBits = memory.getMemBitSize() - lineBits - memory.getBlockBitSize();
        }
        // associativo
        else if (memory.getSets() < memory.getLines())
            memory.tagBits = memory.getMemBitSize() - memory.getBlockBitSize() - memory.setSizes;
        
        //completamente associativo
        else if (memory.getSets() == 1)
            memory.tagBits = memory.getMemBitSize() - memory.blockBitSize;
    
        ArrayList <Integer> chacheSetList = new ArrayList<Integer>();

        for (int adrs : memory.getAddress()) {

            int adrSet = adrs / memory.getLineBytes();
            if (chacheSetList.contains(adrSet))
                memory.cacheHit++;
            else {
                chacheSetList.add(adrSet);
                memory.cacheMiss++;
            }
        }
        
        String answer = "" + memory.blockBitSize + "\n" + memory.setSizes + "\n" + memory.tagBits + "\n" + memory.cacheMiss + "\n" +  memory.cacheHit;
        return answer;
    }
}
