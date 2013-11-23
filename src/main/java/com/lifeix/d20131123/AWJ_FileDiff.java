package com.lifeix.d20131123;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;



public class AWJ_FileDiff
{
	public AWJ_FileDiff(String fromFile, String toFile)
	{
		String[] aLines = read(fromFile);
		String[] bLines = read(toFile);
		List<Difference> diffs  = (new Diff<String>(aLines, bLines)).diff();

		for (Difference diff : diffs) {
			int delStart = diff.getDeletedStart();
			int delEnd = diff.getDeletedEnd();
			int addStart = diff.getAddedStart();
			int addEnd = diff.getAddedEnd();
			String from = toString(delStart, delEnd);
			String to = toString(addStart, addEnd);
			//String type = delEnd != Difference.NONE && addEnd != Difference.NONE ? "c" : (delEnd == Difference.NONE ? "a" : "d");

			System.out.println(from + "-" + to);

			if (delEnd != Difference.NONE) {
				printLines(delStart, delEnd, "<", aLines);
				if (addEnd != Difference.NONE) {
					System.out.println("---");
				}
			}
			if (addEnd != Difference.NONE) {
				printLines(addStart, addEnd, ">", bLines);
			}
		}
	}

	protected void printLines(int start, int end, String ind, String[] lines)
	{
		for (int lnum = start; lnum <= end; ++lnum) {
			System.out.println(ind + " " + lines[lnum]);
		}
	}

	protected String toString(int start, int end)
	{
		// adjusted, because file lines are one-indexed, not zero.

		StringBuffer buf = new StringBuffer();

		// match the line numbering from diff(1):
		buf.append(end == Difference.NONE ? start : (1 + start));

		if (end != Difference.NONE && start != end) {
			buf.append(",").append(1 + end);
		}
		return buf.toString();
	}

	protected String[] read(String fileName)
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			List<String> contents = new ArrayList<String>();

			String in;
			while ((in = br.readLine()) != null) {
				contents.add(in);
			}
			return (String[])contents.toArray(new String[] {});
		}
		catch (Exception e) {
			System.err.println("error reading " + fileName + ": " + e);
			System.exit(1);
			return null;
		}        
	}

	public static void main(String[] args)
	{
		new AWJ_FileDiff("D:/diff/A.txt", "D:/diff/B.txt");
	}

}

class Difference{
    public static final int NONE = -1;
    
    /**
     * The point at which the deletion starts.
     */
    private int delStart = NONE;

    /**
     * The point at which the deletion ends.
     */
    private int delEnd = NONE;

    /**
     * The point at which the addition starts.
     */
    private int addStart = NONE;

    /**
     * The point at which the addition ends.
     */
    private int addEnd = NONE;

    /**
     * Creates the difference for the given start and end points for the
     * deletion and addition.
     */
    public Difference(int delStart, int delEnd, int addStart, int addEnd)
    {
        this.delStart = delStart;
        this.delEnd   = delEnd;
        this.addStart = addStart;
        this.addEnd   = addEnd;
    }

    /**
     * The point at which the deletion starts, if any. A value equal to
     * <code>NONE</code> means this is an addition.
     */
    public int getDeletedStart()
    {
        return delStart;
    }

    /**
     * The point at which the deletion ends, if any. A value equal to
     * <code>NONE</code> means this is an addition.
     */
    public int getDeletedEnd()
    {
        return delEnd;
    }

    /**
     * The point at which the addition starts, if any. A value equal to
     * <code>NONE</code> means this must be an addition.
     */
    public int getAddedStart()
    {
        return addStart;
    }

    /**
     * The point at which the addition ends, if any. A value equal to
     * <code>NONE</code> means this must be an addition.
     */
    public int getAddedEnd()
    {
        return addEnd;
    }

    /**
     * Sets the point as deleted. The start and end points will be modified to
     * include the given line.
     */
    public void setDeleted(int line)
    {
        delStart = Math.min(line, delStart);
        delEnd   = Math.max(line, delEnd);
    }

    /**
     * Sets the point as added. The start and end points will be modified to
     * include the given line.
     */
    public void setAdded(int line)
    {
        addStart = Math.min(line, addStart);
        addEnd   = Math.max(line, addEnd);
    }

    /**
     * Compares this object to the other for equality. Both objects must be of
     * type Difference, with the same starting and ending points.
     */
    public boolean equals(Object obj)
    {
        if (obj instanceof Difference) {
            Difference other = (Difference)obj;

            return (delStart == other.delStart && 
                    delEnd   == other.delEnd && 
                    addStart == other.addStart && 
                    addEnd   == other.addEnd);
        }
        else {
            return false;
        }
    }

    /**
     * Returns a string representation of this difference.
     */
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append("del: [" + delStart + ", " + delEnd + "]");
        buf.append(" ");
        buf.append("add: [" + addStart + ", " + addEnd + "]");
        return buf.toString();
    }

}
