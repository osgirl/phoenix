// David Lanouette
// Coding problem 1.3 for Phoenix Integration

#include <string>

class Aircraft
{
public:
	Aircraft();
	~Aircraft();

	Geometry* geometry;
	int dryWeightPounds;
	int fuelCapacityGallons;
	int cargoCapacityFeed;
	int maxAirSpeed;

	std::string model;
	std::string make;

	Pilot* pilot;
	Pilot* coPilot;

	int calculateTravelTime(int distanceTraveledMinutes, int cargoLoadPounds, int fuelLoadGallons);
	int minimumAirSpeed(int altitudeFeet, double angleOfAttack);

};
