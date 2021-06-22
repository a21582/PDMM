package com.ipca.formulaworld.model

import androidx.room.*
import java.io.Serializable


//data class bets (val competition: String, val team: String, val odd: String)

/*
@IgnoreExtraProperties
data class bets(val competition: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}

 */

/*
@Entity(tableName = "bets_table")
data class bets(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0 ,
    @ColumnInfo(name = "competition") val competition: String? = "",
    @ColumnInfo(name = "team") val team: String? = "",
    @ColumnInfo(name = "odd") val odd: String? = ""){}

 */

/*
data class Teams(
    val team: String?,
    val odd: String?
)

@Entity(tableName = "bets_table")
data class bets(
    @PrimaryKey (autoGenerate = true) val id: Int? = 0,
    val competition: String?,
    @Embedded val teams: Map<Teams>?
): Serializable

 */

@Entity(tableName = "bets_table")
data class Bets(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    @ColumnInfo(name = "object_id") val objectId: String? = "",
    @ColumnInfo(name = "competition") val competition: String? = "",
    @ColumnInfo(name = "team") val team: String? = "",
    @ColumnInfo(name = "odd") val odd: String? = ""
): Serializable


 /*

@Entity(tableName = "betsteam_table")
data class Teams(
    @PrimaryKey(autoGenerate = true) var teamId: Int? = 0,
    var team: String? = null,
    var odd: String? = null
)
@Entity(tableName = "betscompetition_table")
data class Competition(@PrimaryKey(autoGenerate = true) var competitionId: Int? = 0, var competition: String? = null)

@Entity(tableName = "bets_table")
data class BetsCompetitionAndTeams(
    @Embedded var competition: Competition? = null,
    @Relation(
        parentColumn = "competitionId",
        entityColumn = "teamsId"
    )
    val team: Teams? = null
)

  */

/*
class bets {
    /**set Data*/
    var competition :String? = null
    var team:String? = null
    var odd:String? = null
    constructor(){}

    constructor(competition:String?,team:String?,odd:String?){
        this.competition = competition
        this.team = team
        this.odd = odd
    }
}

 */
