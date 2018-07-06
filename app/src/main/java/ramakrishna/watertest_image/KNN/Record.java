package ramakrishna.watertest_image.KNN;

//Basic Record class
public class Record {
	double[] attributes;
	public int classLabel;
	
	Record(double[] attributes, int classLabel){
		this.attributes = attributes;
		this.classLabel = classLabel;
	}
}
