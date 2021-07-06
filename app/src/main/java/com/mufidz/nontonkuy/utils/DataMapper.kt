package com.mufidz.nontonkuy.utils

import com.mufidz.nontonkuy.entity.MovieEntity

fun List<MovieEntity>?.moviesTransforms(): List<MovieEntity> {
    return this?.asSequence()?.map { it.movieTransform() }?.toList().orEmpty()
}

fun MovieEntity.movieTransform(): MovieEntity {
    return MovieEntity(
        id = this.id ?: 0,
        title = this.title.orEmpty(),
        rating = this.rating ?: 0.0,
        overview = this.overview.orEmpty(),
        releaseDate = this.releaseDate.orEmpty(),
        posterPath = this.posterPath.orEmpty(),
        backdropPath = this.backdropPath.orEmpty(),
        //genres = this.genres.genresTransforms(),
        //runtime = this.runtime ?: 0,
        voteCount = this.voteCount ?: 0
    )
}