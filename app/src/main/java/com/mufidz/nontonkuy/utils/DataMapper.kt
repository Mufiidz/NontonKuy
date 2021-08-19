package com.mufidz.nontonkuy.utils

import com.mufidz.nontonkuy.entity.*
import com.mufidz.nontonkuy.entity.response.*

fun List<MovieEntity>?.moviesTransforms(): List<MovieEntity> {
    return this?.asSequence()?.map { it.movieTransform() }?.toList().orEmpty()
}

fun MovieEntity.movieTransform(): MovieEntity {
    return MovieEntity(
        id = this.id ?: 0,
        title = this.title.orEmpty(),
        rating = this.rating ?: 0.0,
        overview = if (overview.isNullOrEmpty()) "-" else this.overview,
        releaseDate = this.releaseDate.orEmpty(),
        posterPath = this.posterPath.orEmpty(),
        backdropPath = this.backdropPath.orEmpty(),
        genres = this.genres.genresTransforms(),
        runtime = this.runtime ?: 0,
        voteCount = this.voteCount ?: 0
    )
}

fun List<GenreResponse>?.genresTransforms(): List<GenreResponse> {
    return this?.asSequence()?.map { it.genreTransform() }?.toList().orEmpty()
}

fun GenreResponse.genreTransform(): GenreResponse {
    return GenreResponse(id = this.id ?: 0, name = this.name.orEmpty())
}

fun List<CastResponse>?.castsTransforms(): List<CastEntity> {
    return this?.asSequence()?.map { it.castTransform() }?.toList().orEmpty()
}

fun CastResponse.castTransform(): CastEntity {
    return CastEntity(
        id = this.id ?: 0,
        name = this.name.orEmpty(),
        characterName = this.character.orEmpty(),
        gender = this.gender ?: -1,
        profilePath = this.profilePath.orEmpty()
    )
}

fun List<CrewResponse>?.crewsTransforms(): List<CrewEntity> {
    return this?.asSequence()?.map { it.crewTransform() }?.toList().orEmpty()
}

fun CrewResponse.crewTransform(): CrewEntity {
    return CrewEntity(
        id = this.id ?: 0,
        name = this.name.orEmpty(),
        job = this.job.orEmpty(),
        gender = this.gender ?: -1,
        profilePath = this.profilePath.orEmpty()
    )
}

fun CreditsEntity?.creditsTransform(): MutableList<ItemCreditsGroup> {
    val itemCreditsCast = this?.casts.castsToItemCredits()
    val itemCreditsCrew = this?.crews.crewsToItemCredits()
    return mutableListOf<ItemCreditsGroup>().apply {
        add(itemCreditsCast)
        add(itemCreditsCrew)
    }
}

fun List<CastEntity>?.castsToItemCredits(): ItemCreditsGroup {
    val list = this?.asSequence()?.map { it.castTransform() }?.toList().orEmpty()
    return ItemCreditsGroup("Cast", list)
}

fun CastEntity.castTransform(): ItemCreditsEntity {

    return ItemCreditsEntity(
        image = this.getFullPathProfileUrl(),
        name = this.name,
        desc = this.characterName
    )
}

fun List<CrewEntity>?.crewsToItemCredits(): ItemCreditsGroup {
    val list = this?.asSequence()?.map { it.crewTransform() }?.toList().orEmpty()
    return ItemCreditsGroup("Crew", list)
}

fun CrewEntity.crewTransform(): ItemCreditsEntity {
    return ItemCreditsEntity(
        image = this.getFullPathProfileUrl(),
        name = this.name,
        desc = this.job
    )
}