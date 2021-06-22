package com.ipca.formulaworld

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ipca.formulaworld.database.MyDatabase
import com.ipca.formulaworld.model.Pilot
import com.ipca.formulaworld.model.Bets
import com.ipca.formulaworld.model.Team
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

        firestoreDb.collection("bets")
            .get()
            .addOnSuccessListener { result ->
                GlobalScope.launch {
                    for (document in result) {
                        Log.d("bets", "${document.id} => ${document.data}")
                        val checkUpdates = db.betsDao().findByObjectId(document.id)
                        if (checkUpdates != null) {
                            // Update
                        } else {
//                        val checkPilot = db.betsDao().getAll()
                            Log.d("bets", "passou aqui3")

/*
                            document.toObject(bets::class.java)?.let {
                                db.betsDao().insertAll(
                                    it
                                )
                            }
 */

                            db.betsDao().insertAll(
                                Bets(
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
    }
}