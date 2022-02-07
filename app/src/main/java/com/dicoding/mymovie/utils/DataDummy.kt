package com.dicoding.mymovie.utils


import com.dicoding.mymovie.data.source.local.entity.MovieEntity
import com.dicoding.mymovie.data.source.local.entity.TvShowEntity
import com.dicoding.mymovie.data.source.remote.response.movie.DetailMovieResponse
import com.dicoding.mymovie.data.source.remote.response.movie.MovieResponse
import com.dicoding.mymovie.data.source.remote.response.tvshow.DetailTvShowResponse
import com.dicoding.mymovie.data.source.remote.response.tvshow.TvShowResponse

object DataDummy {

    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity( 338952,
                "Fantastic Beasts: The Crimes of Grindelwald",
                "/oqLzqCb2old88cVuXS0SGMHkP7Z.jpg",
                "Adventure, Fantasy, Drama",
                false,
                "2018-11-14",
                6.9,
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "en",
                134
            )
        )


        movies.add(
            MovieEntity(332562,
            "A Star Is Born",
            "/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
            "Drama, Romance",
            false,
            "2018-10-03",
            7.5,
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            "en",
            136
            )
        )

        movies.add(
            MovieEntity(399579,
            "Alita: Battle Angel",
            "/xRWht48C2V8XNfzvPehyClOvDni.jpg",
            "Action, Science Fiction, Adventure",
            false,
            "2019-01-31",
            7.2,
            "Action, Science Fiction, Adventure",
            "en",
            122
            )
        )

        movies.add(
            MovieEntity(297802,
                "Aquaman",
                "/xLPffWMhMj1l50ND3KchMjYoKmE.jpg",
                "Action, Adventure, Fantasy",
                false,
                "2018-07-06",
                6.9,
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "en",
                143
            )
        )

        return movies
    }

    fun generateDummyDetailMovie(): MovieEntity {
        return MovieEntity(
            424,
            "Schindler's List",
            "/sF1U4EUQS8YHUYjNl3pMGNIQyr0.jpg",
            "Drama, Hsitory, War",
            false,
            "1993-11-30",
            8.6,
            "The true story of how businessman Oskar Schindler saved over a thousand Jewish lives from the Nazis while they worked as slaves in his factory during World War II.",
            "en",
            195

        )
    }


    fun generateDummyTvShows(): List<TvShowEntity>{
        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(
            TvShowEntity(60735,
                "The Flash",
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "Drama, Sci-Fi & Fantasy",
                false,
                "2014-10-07",
                7.8,
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only meta-human who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "en",
                44
            )
        )

        tvShows.add(
            TvShowEntity(60708,
                "Gotham",
                "/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg",
                "Drama, Crime, Sci-Fi & Fantasy",
                false,
                "2014-09-22",
                7.6,
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "en",
                43
            )
        )

        tvShows.add(
            TvShowEntity(79501,
                "Doom Patrol",
                "/kOAn06LmRCg4YiSStwrGL6UOQ3a.jpg",
                "Sci-Fi & Fantasy, Drama",
                false,
                "2019-02-15",
                7.7,
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "en",
                49
            )
        )

        tvShows.add(
            TvShowEntity(12609,
                "Dragon Ball",
                "/tZ0jXOeYBWPZ0OWzUhTlYvMF7YR.jpg",
                "Animation, Action & Adventure, Sci-Fi & Fantasy",
                false,
                "1986-02-26",
                8.2,
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                "ja",
                25
            )
        )

        return tvShows
    }

    fun generateDummyDetailTvShow(): TvShowEntity {
        return  TvShowEntity(
            79460,
            "Legacies",
            "/mwJ3OxbliRwCvceSJvAzPiFu4WZ.jpg",
            "Sci-Fi & Fantasy, Drama",
            false,
            "2018-10-25",
            8.5,
            "In a place where young witches, vampires, and werewolves are nurtured to be their best selves in spite of their worst impulses, Klaus Mikaelson’s daughter, 17-year-old Hope Mikaelson, Alaric Saltzman’s twins, Lizzie and Josie Saltzman, among others, come of age into heroes and villains at The Salvatore School for the Young and Gifted.",
            "en",
            43
        )
    }


    //Remote
    fun generateDummyRemoteMovies(): List<MovieResponse> {
        return listOf(
            MovieResponse(
                "Tired of being locked in a reptile house where humans gawk at them like they are monsters, a ragtag group of Australia’s deadliest creatures plot an escape from their zoo to the Outback, a place where they’ll fit in without being judged.",
                "en",
                "Back to the Outback",
                false,
                "Back to the Outback",
                "/zNXNRLH5wJprUG6B1olaBTNZOjy.jpg",
                "2021-12-03",
                7.8,
                770254,
                false,
                92,
            ),
            MovieResponse(
                "On a post-apocalyptic Earth, a robot, built to protect the life of his dying creator's beloved dog, learns about life, love, friendship, and what it means to be human.",
                "en",
                "Finch",
                false,
                "Finch",
                "/jKuDyqx7jrjiR9cDzB5pxzhJAdv.jpg",
                "2021-11-04",
                8.1,
                522402,
                false,
                115
            ),
            MovieResponse(
                "Two low-level astronomers must go on a giant media tour to warn humankind of an approaching comet that will destroy planet Earth.",
                "en",
                "Dont't look up",
                false,
                "Don't look up",
                "/th4E1yqsE8DGpAseLiUrI60Hf8V.jpg",
                "2021-12-07",
                7.3,
                646380,
                false,
                143
            ),

            MovieResponse(
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "en",
                "Bohemian Rhapsody",
                false,
                "Bohemian Rhapsody",
                "/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
                "2018-10-24",
                8.0,
                424694,
                false,
                135
            )
        )
    }

    fun generateDummyRemoteDetailMovie(): DetailMovieResponse {
        return DetailMovieResponse(
            "en",
            "tt0108052",
            false,
            "Schindler;s List",
            "/loRmRzQXZeqG78TqZuyvSlEQfZb.jpg",
            321365567,
             listOf(
                 com.dicoding.mymovie.data.source.remote.response.movie.Genre(
                     "Drama",
                     18
                 ),
                 com.dicoding.mymovie.data.source.remote.response.movie.Genre(
                     "History",
                     36
                 ),
                 com.dicoding.mymovie.data.source.remote.response.movie.Genre(
                     "War",
                     10752
                 )
             ),
            45.66,
            424,
            8.6,
            "The true story of how businessman Oskar Schindler saved over a thousand Jewish lives from the Nazis while they worked as slaves in his factory during World War II.",
            "Schindler's List",
            195,
            "/sF1U4EUQS8YHUYjNl3pMGNIQyr0.jpg",
            "1993-11-30",
            false
        )
    }


    fun generateDummyRemoteTvShows(): List<TvShowResponse> {
        return listOf(
            TvShowResponse(
                "2018-06-20",
                "To the Duttons, family is everything. However, newfound truths threaten that bond. Jimmy comes home, and has important decisions to make. Beth takes family matters into her own hands.",
                "en",
                "/iqWCUwLcjkVgtpsDLs8xx8kscg6.jpg",
                "Yellowstone",
                8.0,
                "Yellowstone",
                73586,
            ),
            TvShowResponse(
                "2019-12-20",
                "Geralt faces off with a demon targeting his nearest and dearest while the most powerful players on the Continent ramp up their pursuit of Ciri.",
                "en",
                "/7vjaCdMw15FEbXyLQTVa04URsPm.jpg",
                "The Witcher",
                8.2,
                "The Witcher",
                71912
                ),
            TvShowResponse(
                "2021-02-22",
                "Chain Reaction is an American game show created by Bob Stewart, in which players compete to form chains composed of two-word phrases.\n\nThe show aired three separate runs: Bill Cullen hosted the original series on NBC from January 14 to June 20, 1980. The second version aired on the USA Network from September 29, 1986 to December 27, 1991 and was hosted first by Blake Emmons and later by Geoff Edwards. Another version, hosted by Dylan Lane, premiered on August 1, 2006 on GSN. This version aired its final original episode on June 9, 2007 but has continued to air in reruns since, currently airing Mondays through Fridays at 3:00 PM and 3:30 PM ET on GSN. Starting August 2, 2013, it will air Fridays at 8:00 PM to 10:00 PM on GSN.",
                "en",
                "/xhjoXm1WEvfQGYBGXKk8xk75z6s.jpg",
                "Chain Reaction",
                1.5,
                "Chain Reaction",
                6455
            ),

            TvShowResponse(
                "2012-10-10",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "en",
                "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                "Arrow",
                6.7,
                "Arrow",
                1412,
            )
        )
    }

    fun generateDummyRemoteDetailTvShow(): DetailTvShowResponse {
        return DetailTvShowResponse(
            "en",
            listOf(
                com.dicoding.mymovie.data.source.remote.response.tvshow.Genre(
                    "Sci-Fi & Fantasy",
                    10765
                ),
                com.dicoding.mymovie.data.source.remote.response.tvshow.Genre(
                    "Drama",
                    18
                )
            ),
            457.014,
            79460,
            "2018-10-25",
            "In a place where young witches, vampires, and werewolves are nurtured to be their best selves in spite of their worst impulses, Klaus Mikaelson’s daughter, 17-year-old Hope Mikaelson, Alaric Saltzman’s twins, Lizzie and Josie Saltzman, among others, come of age into heroes and villains at The Salvatore School for the Young and Gifted.",
            "/mwJ3OxbliRwCvceSJvAzPiFu4WZ.jpg",
            "Legacies",
            8.5,
            "Legacies",
            listOf(43),
            false
            )
    }
}