package fr.unice.miage.m1.tp5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class created on 09/01/2017
 *
 * @author JuIngong
 */
public class ChordPeer {

    private int myId;

    private ChordPeer pred;

    private ChordPeer succ;

    private Map<Integer, String> dictionnary;

    private List<ChordPeer> fingerTable;

    public ChordPeer(int myId, ChordPeer pred, ChordPeer succ) {
        this.myId = myId;
        this.pred = pred;
        this.succ = succ;
        this.dictionnary = new HashMap<>();
    }

    public ChordPeer(int myId) {
        this.myId = myId;
        this.pred = null;
        this.succ = null;
        this.dictionnary = new HashMap<>();
    }

    public String getItem(int key) {
        ChordPeer c = findKey(key);
        System.out.println(c.myId);
        return c.dictionnary.get(key);
    }

    public void setItem(int key, String string) {
        ChordPeer c = findKey(key);
        c.dictionnary.put(key, string);
    }

    public void joinChord(ChordPeer handle) {
        ChordPeer c = this.findKey(handle.getId());
        if (pred == null && succ == null) {
            c.pred = handle;
            c.succ = handle;
            handle.pred = c;
            handle.succ = c;

        } else {
            c.pred.succ = handle;
            handle.pred = c.pred;
            handle.succ = c;
            handle.dictionnary = c.dictionnary.entrySet().stream().filter(e -> e.getKey() <= c.getId()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            c.dictionnary.values().removeAll(handle.dictionnary.values());
            c.pred = handle;
        }
    }

    public void leaveChord(){
        this.succ.dictionnary.putAll(this.dictionnary);
        this.dictionnary.clear();
        this.pred.succ = this.succ;
        this.succ.pred = this.pred;
        this.pred = null;
        this.succ = null;
    }

    public ChordPeer findKey(int key) {
        if (pred == null || succ == null) {
            return this;
        } else if ((key > pred.getId() || (pred.getId() > myId && key < pred.getId())) && key <= myId) {
            return this;
        } else if (key > myId && myId > succ.getId()) {
            return succ;
        } else {
            return succ.findKey(key);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("myId=").append(myId);
        if (pred != null) {
            sb.append(", pred=").append(pred.getId());
        }
        if (succ != null) {
            sb.append(", succ=").append(succ.getId());
        }
        sb.append('}');
        return sb.toString();
    }

    public int getId() {
        return myId;
    }

    public ChordPeer getPred() {
        return pred;
    }

    public ChordPeer getSucc() {
        return succ;
    }
}
