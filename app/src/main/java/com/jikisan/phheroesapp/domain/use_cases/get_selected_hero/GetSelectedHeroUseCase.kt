package com.jikisan.phheroesapp.domain.use_cases.get_selected_hero

import com.jikisan.phheroesapp.data.repository.Repository
import com.jikisan.phheroesapp.domain.model.Hero

class GetSelectedHeroUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(heroId: Int): Hero {
        return repository.getSelectedHero(heroId = heroId)
    }
}