package fr.unice.miage.m1.tp5;

/**
 * Class created on 09/01/2017
 *
 * @author JuIngong
 */
public class ChordPeer {

    private int myId;

    private ChordPeer pred;

    private ChordPeer succ;


    public ChordPeer(int myId, ChordPeer pred, ChordPeer succ) {
        this.myId = myId;
        this.pred = pred;
        this.succ = succ;
    }

    public ChordPeer(int myId) {
        this.myId = myId;
        this.pred = null;
        this.succ = null;
    }

    public ChordPeer insertChord(int key) {
        ChordPeer c = this.findKey(key);
        ChordPeer c2 = new ChordPeer(key);
        if (pred == null && succ == null) {
            c.pred = c2;
            c.succ = c2;
            c2.pred = c;
            c2.succ = c;

        } else {
            c.pred.succ = c2;
            c2.pred = c.pred;
            c2.succ = c;
            c.pred = c2;
        }
        return c2;
    }

    public ChordPeer findKey(int key) {
        if (pred == null || succ == null) {
            return this;
        } else if (key > pred.getId() && key <= myId) {
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
