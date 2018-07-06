package ramakrishna.watertest_image.KNN;//basic metric interface

public interface Metric {
	double getDistance(Record s, Record e);
}
