package ballboy.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *  change levels
 *  1. move to next level
 *  2. change to the level stored in memento
 */
public class ChangeLevel {
    private JSONArray levelConfigs;
    private int curr;
    private int maxL;

    private JSONObject levelConfig;
    private Level level;
    private boolean flag = false;

    public ChangeLevel(JSONArray levelConfigs,
                       int levelIndex){
        this.levelConfigs = levelConfigs;
        this.curr = levelIndex;
        this.maxL = levelConfigs.size() - 1;
    }

    public boolean nextLevel(){
        if(curr < maxL){
            curr++;
            return true;
        }
        return false;
    }

    public void setLevel(int n){
        if(n >= 0 && n <= maxL){
            curr = n;
        }
    }

    public JSONObject getLevel(){
        levelConfig = (JSONObject) levelConfigs.get(curr);
        return levelConfig;
    }

    public void flag() {
        if (curr == maxL) {
            flag = true;
        }
    }

    public boolean finish(){
        if(curr == maxL && flag == true){
            return true;
        }
        return false;
    }

    public int getCurr(){
        return curr;
    }



}
