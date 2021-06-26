package com.ipca.formulaworld

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ipca.formulaworld.database.MyDatabase
import com.ipca.formulaworld.model.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FirestoreSetup {
    fun setup(db: MyDatabase) {
        val firestoreDb = Firebase.firestore

        // Firestore sync

        firestoreDb.collection("pilots")
            .get()
            .addOnSuccessListener { result ->
                GlobalScope.launch {
                    for (document in result) {
                        val checkPilot = db.pilotDao().findByObjectId(document.id)
                        if (checkPilot != null) {
                            // Update data
                            db.pilotDao().updatePilot(
                                Pilot(
                                    checkPilot.id,
                                    document.id,
                                    document.data["name"].toString(),
                                    document.data["photo"].toString(),
                                    document.data["classification"].toString(),
                                    document.data["year"].toString(),
                                )
                            )
                        } else {
                            db.pilotDao().insertAll(
                                Pilot(
                                    null,
                                    document.id,
                                    document.data["name"].toString(),
                                    document.data["photo"].toString(),
                                    document.data["classification"].toString(),
                                    document.data["year"].toString(),
                                )
                            )
                        }
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Pilots", "Error getting documents.", exception)
            }

        firestoreDb.collection("bets_competition")
            .get()
            .addOnSuccessListener { result ->
                GlobalScope.launch {
                    for (document in result) {
                        val checkUpdates = db.betsCompetitionDao().findByObjectId(document.id)
                        if (checkUpdates != null) {
                            // Update
                        } else {
                            db.betsCompetitionDao().insertAll(
                                BetsCompetition(
                                    null,
                                    document.id,
                                    document.data["competition"].toString()
                                )
                            )

                        }

                    }


                }
            }
            .addOnFailureListener { exception ->
                Log.w("Bets", "Error getting documents.", exception)
            }

        firestoreDb.collection("bets_teams")
            .get()
            .addOnSuccessListener { result ->
                GlobalScope.launch {
                    for (document in result) {
                        val checkUpdates = db.betsTeamsDao().findByObjectId(document.id)
                        if (checkUpdates != null) {
                            // Update
                        } else {
                            db.betsTeamsDao().insertAll(
                                BetsTeams(
                                    null,
                                    document.id,
                                    document.data["competition"].toString(),
                                    document.data["team"].toString(),
                                    document.data["odd"].toString()
                                )
                            )

                        }

                    }


                }
            }
            .addOnFailureListener { exception ->
                Log.w("Bets", "Error getting documents.", exception)
            }

        firestoreDb.collection("bets_player")
            .get()
            .addOnSuccessListener { result ->
                GlobalScope.launch {
                    for (document in result) {
                        val checkUpdates = db.betsPlayersDao().findByObjectId(document.id)
                        if (checkUpdates != null) {
                            // Update
                        } else {
                            db.betsPlayersDao().insertAll(
                                BetsPlayers(
                                    null,
                                    document.id,
                                    document.data["competition"].toString(),
                                    document.data["player"].toString(),
                                    document.data["odd"].toString(),
                                )
                            )

                        }

                    }


                }
            }
            .addOnFailureListener { exception ->
                Log.w("Bets", "Error getting documents.", exception)
            }


        firestoreDb.collection("teams")
            .get()
            .addOnSuccessListener { result ->
                    GlobalScope.launch {
                        for (document in result) {
                    Log.d("TeamId", document.id)
                    val checkTeam = db.teamDao().findByObjectId(document.id)
                    if(checkTeam != null) {
                        // Update data
                        db.teamDao().updateTeam(Team(
                            checkTeam.id,
                            document.id,
                            document.data["name"].toString(),
                            document.data["photo"].toString(),
                            document.data["classification"].toString(),
                            document.data["year"].toString(),
                        ))
                    } else {
                        db.teamDao().insertAll(
                            Team(
                                null,
                                document.id,
                                document.data["name"].toString(),
                                document.data["photo"].toString(),
                                document.data["classification"].toString(),
                                document.data["year"].toString(),
                            )
                        )
                    }
                }
            }
        }
            .addOnFailureListener { exception ->
                Log.w("Teams", "Error getting documents.", exception)
            }

        firestoreDb.collection("events")
            .get()
            .addOnSuccessListener { result ->
                GlobalScope.launch {
                    for (document in result) {
                        val checkUpdates = db.eventsDao().findByObjectId(document.id)
                        if (checkUpdates != null) {
                            // Update
                        } else {
                            db.eventsDao().insertAll(
                                Events(
                                    null,
                                    document.id,
                                    document.data["event_day"].toString(),
                                    document.data["event_desc"].toString(),
                                    document.data["simple_date"].toString(),
                                    document.data["title"].toString(),
                                    document.data["image"].toString()
                                )
                            )

                        }

                    }


                }
            }
            .addOnFailureListener { exception ->
                Log.w("Bets", "Error getting documents.", exception)
            }

        firestoreDb.collection("cars")
            .get()
            .addOnSuccessListener { result ->
                GlobalScope.launch {
                    for (document in result) {

                        Log.d("CarId", document.id)
                        val checkCar = db.carDao().findByObjectId(document.id)
                        if(checkCar != null) {
                            // Update data
                            db.carDao().updateCar(Car(
                                checkCar.id,
                                document.id,
                                document.data["name"].toString(),
                                document.data["photo"].toString(),
                                document.data["classification"].toString(),
                                document.data["year"].toString(),
                            ))
                        } else {
                            db.carDao().insertAll(
                                Car(
                                    null,
                                    document.id,
                                    document.data["name"].toString(),
                                    document.data["photo"].toString(),
                                    document.data["classification"].toString(),
                                    document.data["year"].toString(),
                                )
                            )
                        }
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Teams", "Error getting documents.", exception)
            }
    }
}