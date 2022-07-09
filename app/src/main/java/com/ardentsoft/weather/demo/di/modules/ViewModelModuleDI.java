package com.ardentsoft.weather.demo.di.modules;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.ardentsoft.weather.demo.domain.usecase.GetCityUseCase;
import com.ardentsoft.weather.demo.domain.usecase.GetCityWeatherUseCase;
import com.ardentsoft.weather.demo.netowrkservice.INetworkClientService;
import com.ardentsoft.weather.demo.presentation.utils.ViewModelFactory;
import com.ardentsoft.weather.demo.presentation.viewmodel.CityViewModel;
import com.ardentsoft.weather.demo.presentation.viewmodel.CityWeatherViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelModuleDI {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        @NonNull Class<? extends ViewModel> value();
    }

    @NonNull
    @Provides
    ViewModelFactory viewModelFactory(@NonNull Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @NonNull
    @Provides
    @IntoMap
    @ViewModelKey(CityWeatherViewModel.class)
    ViewModel cityWeatherViewModel(@NonNull GetCityWeatherUseCase getCityWeatherUseCase, @NonNull INetworkClientService iNetworkClientService) {
        return new CityWeatherViewModel(getCityWeatherUseCase, iNetworkClientService);
    }

    @NonNull
    @Provides
    @IntoMap
    @ViewModelKey(CityViewModel.class)
    ViewModel cityViewModel(@NonNull GetCityUseCase getCityUseCase, @NonNull INetworkClientService iNetworkClientService) {
        return new CityViewModel(getCityUseCase, iNetworkClientService);
    }
}
