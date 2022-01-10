package com.getman.varnabeach;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

import android.content.res.Resources;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
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

        BeachConditionsActivity.UnitFormat format = new BeachConditionsActivity.UnitFormat(resources);
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

        BeachConditionsActivity.UnitFormat format = new BeachConditionsActivity.UnitFormat(resources);
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
}