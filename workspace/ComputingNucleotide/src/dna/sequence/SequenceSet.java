package dna.sequence;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.crypto.CipherInputStream;

import org.yeastrc.fasta.FASTAEntry;
import org.yeastrc.fasta.FASTAHeader;
import org.yeastrc.fasta.FASTAReader;
import org.yeastrc.*;
// This class represents a set of sequences.
public class SequenceSet {
	
	LinkedList<Sequence> sequenceSet;
	// Constructor.
	public SequenceSet() {
		sequenceSet = new LinkedList<Sequence>();
	}
	
	// Load sequences from FASTA file.
	public static SequenceSet load(String fileName) throws FileNotFoundException{
		SequenceSet seqSet = new SequenceSet();
		FASTAReader reader;
		String seq = "", header = "";
		try {
			reader = FASTAReader.getInstance( fileName );
			FASTAEntry entry = reader.readNext();
			int counter =1;
			while( entry != null ) {
				// show the sequence for this FASTA entry
				seq = entry.getSequence();
				for( FASTAHeader headerStr : entry.getHeaders() ) {
					header= headerStr.getName();
				}
				Sequence newSeq = null;
				newSeq = new Sequence(header, seq);
				if(!seqSet.sequenceSet.contains(newSeq) && newSeq != null) {
					seqSet.sequenceSet.insert(newSeq);
				}
				// get the next entry in the FASTA file
				entry = reader.readNext();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return seqSet;
	}

	
	// Return the global usage over all sequences in the set. The word length is k and window step size is w.
	public Usage getUsage(int k, int w){
		Usage usage = new Usage();
		Iterator<Sequence> seqItr = sequenceSet.iterator();	
		int count = 0;
		while(seqItr.hasNext()) {
//			System.out.println(++count+" : " +sequenceSet.size());
			usage.add(seqItr.next().getUsage(k, w));
		}
		
		return usage;
	}
	// Return all sequences in the set in the same order they appear in the file.
	public LinkedList<Sequence> getSequences(){
		LinkedList<Sequence> sequences = new LinkedList<Sequence>();
		Iterator<Sequence> seqItr = sequenceSet.iterator();
		
		while(seqItr.hasNext()) {
			sequences.insert(seqItr.next());
		}
		return sequences;
	}
	
	@Override
	public String toString() {
		StringBuffer sbr = new StringBuffer();
		sbr.append("Sequence Set has: "+sequenceSet.size()+ " sequence\n");
		LinkedList<Sequence> sequences = getSequences();
		Iterator<Sequence> seqItr = sequenceSet.iterator();
//		while(seqItr.hasNext()) {
//			sbr.append(seqItr.next().toString());
//		}
		return sbr.toString()+"\n\n";
	}
}
