package com.getman.varnabeach;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

import android.content.res.Resources;

public class UnitFormatTest {
    private Resources resources;

    @Before
    public void setMocks() {
        resources = Mockito.mock(Resources.class);
    }

    @Test
    public void returnsCorrectUnitEnglish() {
        // given
        setMocksValuesEnglish();

        BeachConditionsFragment.UnitFormat format = new BeachConditionsFragment.UnitFormat(resources);
        String temperature = "airTemperature";
        String height = "waveHeight";
        // when
        String celsius = format.getUnitFor(temperature);
        String meters = format.getUnitFor(height);
        // then
        assertEquals("°C", celsius);
        assertEquals("m", meters);
    }

    private void setMocksValuesEnglish() {
        Mockito.when(resources.getString(R.string.speed_unit)).thenReturn("m/s");
        Mockito.when(resources.getString(R.string.distance_unit)).thenReturn("m");
        Mockito.when(resources.getString(R.string.temperature_unit)).thenReturn("°C");
    }

    @Test
    public void returnsCorrectUnitRussian() {
        // given
        setMocksRussian();

        BeachConditionsFragment.UnitFormat format = new BeachConditionsFragment.UnitFormat(resources);
        String temperature = "airTemperature";
        String height = "waveHeight";
        // when
        String celsius = format.getUnitFor(temperature);
        String meters = format.getUnitFor(height);
        // then
        assertEquals("°C", celsius);
        assertEquals("м", meters);
    }

    private void setMocksRussian() {
        Mockito.when(resources.getString(R.string.speed_unit)).thenReturn("м/с");
        Mockito.when(resources.getString(R.string.distance_unit)).thenReturn("м");
        Mockito.when(resources.getString(R.string.temperature_unit)).thenReturn("°C");
    }

    @Test
    public void buildsNormalStringFromCamelCaseCorrectly() {
        // given
        String camel = "camelCaseExample";
        // when
        String normal = BeachConditionsFragment.UnitFormat.convertCamelCaseToNormal(camel);
        // then
        assertEquals("Camel Case Example ", normal);
    }
}