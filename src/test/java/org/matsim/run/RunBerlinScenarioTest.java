/* *********************************************************************** *
 * project: org.matsim.*												   *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2008 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */
package org.matsim.run;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.matsim.analysis.ScoreStatsControlerListener.ScoreItem;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting;
import org.matsim.testcases.MatsimTestUtils;

/**
 * @author ikaddoura
 *
 */
public class RunBerlinScenarioTest {
	@Rule public MatsimTestUtils utils = new MatsimTestUtils() ;
	
	// 10pct, testing the scores in iteration 0
	@Ignore
	@Test
	public final void test1() {
		try {
			Config config;

			String configFile = "scenarios/berlin-v5.0-10pct-2018-06-18/input/berlin-5.0_config.xml";
			config = ConfigUtils.loadConfig(configFile);
			config.controler().setLastIteration(0);
			config.controler().setOverwriteFileSetting(OverwriteFileSetting.deleteDirectoryIfExists);
			config.controler().setOutputDirectory( utils.getOutputDirectory() );
			
			Controler controler = RunBerlinScenario.run(config);
			
			Assert.assertEquals("Wrong avg. AVG score in iteration 0.", 115.776237215495, controler.getScoreStats().getScoreHistory().get(ScoreItem.average).get(0), MatsimTestUtils.EPSILON);
			
		} catch ( Exception ee ) {
			Logger.getLogger(this.getClass()).fatal("there was an exception: \n" + ee ) ;			
			Assert.fail("Wasn't able to run the berlin scenario.");
		}
	}
	
	// 1pct, testing the scores in the first and second iteration
	@Test
	public final void test2() {
		try {
			Config config;

			String configFile = "scenarios/berlin-v5.0-1pct-2018-06-18/input/berlin-5.0_config.xml";
			config = ConfigUtils.loadConfig(configFile);
			config.controler().setLastIteration(1);
			config.strategy().setFractionOfIterationsToDisableInnovation(1.);
			config.controler().setOverwriteFileSetting(OverwriteFileSetting.deleteDirectoryIfExists);
			config.controler().setOutputDirectory( utils.getOutputDirectory() );
			
			Controler controler = RunBerlinScenario.run(config);
			
			Assert.assertEquals("Wrong avg. AVG score in iteration 0.", 111.84928125172443, controler.getScoreStats().getScoreHistory().get(ScoreItem.average).get(0), MatsimTestUtils.EPSILON);
			Assert.assertEquals("Wrong avg. AVG score in iteration 0.", 109.27928497494945, controler.getScoreStats().getScoreHistory().get(ScoreItem.average).get(1), MatsimTestUtils.EPSILON);

		} catch ( Exception ee ) {
			Logger.getLogger(this.getClass()).fatal("there was an exception: \n" + ee ) ;			
			Assert.fail("Wasn't able to run the berlin scenario.");
		}
	}
	
	// 0.1pct, testing the scores in the first and second iteration
	@Test
	public final void test3() {
		try {
			Config config;

			String configFile = "scenarios/berlin-v5.0-0.1pct-2018-06-18/input/berlin-5.0_config.xml";
			config = ConfigUtils.loadConfig(configFile);
			config.controler().setLastIteration(1);
			config.strategy().setFractionOfIterationsToDisableInnovation(1.);
			config.controler().setOverwriteFileSetting(OverwriteFileSetting.deleteDirectoryIfExists);
			config.controler().setOutputDirectory( utils.getOutputDirectory() );
			
			Controler controler = RunBerlinScenario.run(config);
			
			Assert.assertEquals("Wrong avg. AVG score in iteration 0.", 84.66967102654722, controler.getScoreStats().getScoreHistory().get(ScoreItem.average).get(0), MatsimTestUtils.EPSILON);
			Assert.assertEquals("Wrong avg. AVG score in iteration 0.", 84.76497660204434, controler.getScoreStats().getScoreHistory().get(ScoreItem.average).get(1), MatsimTestUtils.EPSILON);

		} catch ( Exception ee ) {
			Logger.getLogger(this.getClass()).fatal("there was an exception: \n" + ee ) ;			
			Assert.fail("Wasn't able to run the berlin scenario.");
		}
	}
	
	// 1pct, testing the 100th iteration
	@Test
	public final void test4() {
		try {
			Config config;

			String configFile = "scenarios/berlin-v5.0-1pct-2018-06-18/input/berlin-5.0_config.xml";
			config = ConfigUtils.loadConfig(configFile);
			config.controler().setLastIteration(100);
			config.controler().setOverwriteFileSetting(OverwriteFileSetting.deleteDirectoryIfExists);
			config.controler().setOutputDirectory( utils.getOutputDirectory() );
			
			Controler controler = RunBerlinScenario.run(config);
			
			Assert.assertEquals("Wrong avg. AVG score in iteration 100.", 114.45259157449782, controler.getScoreStats().getScoreHistory().get(ScoreItem.average).get(100), MatsimTestUtils.EPSILON);
			// TODO: add test for modal split and / or other calibration values
			
		} catch ( Exception ee ) {
			Logger.getLogger(this.getClass()).fatal("there was an exception: \n" + ee ) ;			
			Assert.fail("Wasn't able to run the berlin scenario.");
		}
	}
}
