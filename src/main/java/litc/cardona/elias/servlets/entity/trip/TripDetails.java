package litc.cardona.elias.servlets.entity.trip;

import com.google.gson.annotations.SerializedName;


public class TripDetails {
    @SerializedName("tdate")
	private String InitialTime;
	private float distance;
	private float speed;

    public TripDetails() {}

    public TripDetails(String InitialTime, float distance, float speed) {
        this.InitialTime = InitialTime;
		this.distance = distance;
		this.speed = speed;
    }

	public String getInitialTime() {
		return InitialTime;
	}

	public void setInitialTime(String InitialTime) {
		this.InitialTime = InitialTime;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
