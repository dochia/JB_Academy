package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
        allDrivers
                .filterNot { trips
                        .map{ trip -> trip.driver }
                        .contains(it) }
                .toSet()

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> {
    if (minTrips == 0)
        return allPassengers
    return trips
            .map{trip -> trip.passengers}
            .flatten()
            .groupBy{it}
            .mapValues { (_, list) -> list.size }
            .filterValues {it >= minTrips}.keys
}

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
        trips
            .filter{trip -> trip.driver == driver}
            .map{trip -> trip.passengers}
            .flatten()
            .groupBy { it }
            .mapValues { (_, list) -> list.size }
            .filterValues { it > 1 }.keys


/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> {
   val allPassTrips = allPassengers.associateWith {pass ->
        trips.filter{trip -> pass in trip.passengers}}
    val allPassTripsWithDiscounts = allPassengers.associateWith { pass ->
        trips.filter{ trip-> (trip.discount != null && trip.discount > 0.0 && pass in trip.passengers)} }

    return allPassTrips.filter {
        (pass, list) ->
            val listWith = allPassTripsWithDiscounts[pass]
        listWith?.isNotEmpty()!! && (listWith.size > list.size / 2)
     }.keys
    }

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    val tripsDivided  = trips.groupBy { trip -> IntRange(trip.duration / 10 * 10, trip.duration / 10 * 10 + 9 ) }
    return tripsDivided.maxBy { (_, list) -> list.size}?.key
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (trips.isEmpty() )
        return false
    val total = trips.map{trip -> trip.cost }.sum()
    val count = (allDrivers.size * .2).toInt()
    val allDriversTotal = allDrivers.associateWith {
        driver ->
        trips.filter{ trip -> trip.driver == driver}
                .sumByDouble{trip -> trip.cost} }
            .toList()
            .sortedBy{ (_, value) -> value}
            .toMap()
    val number = allDriversTotal.values.toDoubleArray().takeLast(count).sumByDouble{value -> value}
    return number >= total * 0.8
}