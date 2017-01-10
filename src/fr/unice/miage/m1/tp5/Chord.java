package fr.unice.miage.m1.tp5;

import java.util.ArrayList;
import java.util.List;

/**
 * Class created on 09/01/2017
 *
 * @author JuIngong
 */
public class Chord {

    private ChordPeer chordPeer;

    public Chord(List<Integer> chordIds) {
        buildChord(chordIds);
    }

    private void buildChord(List<Integer> chordIds) {
        for (Integer i : chordIds) {
            if (chordPeer == null) {
                chordPeer = new ChordPeer(i);
            } else {
                chordPeer.joinChord(new ChordPeer(i));
            }
        }
    }

    public ChordPeer findKey(int key) {
        return chordPeer.findKey(key);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Chord{");
        ChordPeer c = chordPeer;
        sb.append("chordPeer=").append(c);
        while (chordPeer.getId() != (c = c.getSucc()).getId()) {
            sb.append(", chordPeer=").append(c);
        }
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(13);
        list.add(25);
        list.add(96);
        list.add(45);
        Chord c = new Chord(list);
        c.chordPeer.setItem(7, "Blabla");
        c.chordPeer.setItem(8, "Blabla8");
        c.chordPeer.setItem(34, "Tarfff");
        c.chordPeer.setItem(46, "Teteaaaaa");
        c.chordPeer.setItem(45, "Fpppppp");
        System.out.println(c.toString());
        System.out.println(c.chordPeer.getItem(7));
        System.out.println(c.chordPeer.getItem(46));
        System.out.println(c.chordPeer.getItem(34));
        ChordPeer test = new ChordPeer(35);
        c.chordPeer.joinChord(test);
        System.out.println(c.chordPeer.getItem(34));
        test.leaveChord();
        System.out.println(c.chordPeer.getItem(34));
        System.out.println(c.findKey(13));
        System.out.println(c.findKey(35));
    }
}
