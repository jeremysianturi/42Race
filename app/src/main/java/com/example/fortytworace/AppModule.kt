package com.example.fortytworace

import com.example.core.domain.usecase.businesses.BusinessesInteractor
import com.example.core.domain.usecase.businesses.BusinessesUsecase
import com.example.core.domain.usecase.reviewbusiness.BusinessReviewInteractor
import com.example.core.domain.usecase.reviewbusiness.BusinessReviewUsecase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideBusinessUsecase(businessInteractor: BusinessesInteractor): BusinessesUsecase

    @Binds
    abstract fun provideBusinessReviewUsecase(businessReviewInteractor: BusinessReviewInteractor): BusinessReviewUsecase

}