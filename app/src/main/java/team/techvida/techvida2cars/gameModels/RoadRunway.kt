package team.techvida.techvida2cars.gameModels

sealed class RoadRunway {
    object Left : RoadRunway()
    object  Right : RoadRunway()
}