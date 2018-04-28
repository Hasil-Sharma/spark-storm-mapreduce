package storm.util;

import java.util.*;

public class HashTagUtil {

    Map<String,List<String>> teamToHashTagMap = new HashMap<>();
    Map<String,List<String>> teamToHandleMap = new HashMap<>();

    List<String> cskTags = new ArrayList<>();
    List<String> cskHandles = new ArrayList<>();

    public HashTagUtil(){
        cskTags.add("csk");
        cskTags.add("yellove");
        cskTags.add("whistlepodu");
        cskTags.add("chennaisuperkings");

        cskHandles.add("");
        teamToHashTagMap.put("CSK",cskTags);
        teamToHandleMap.put("CSK",cskHandles);
    }

    public List<String> getHandlesForTeam(String team) {
        return teamToHandleMap.get(team);
    }

    public List<String> getTagList(String team){
        return teamToHashTagMap.get(team);
    }

}
