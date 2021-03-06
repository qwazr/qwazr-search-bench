/**
 * Copyright 2017 Emmanuel Keller / QWAZR
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qwazr.search.bench.test.TaxonomyFacet;

import com.qwazr.search.bench.test.BaseTest;
import com.qwazr.search.bench.test.CommonTestSuite;
import com.qwazr.search.bench.test.TestSettings;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by ekeller on 15/02/2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TaxonomyFacetTestSuite.LuceneNoExecutor.class,
		TaxonomyFacetTestSuite.LuceneWithExecutor.class,
		TaxonomyFacetTestSuite.Qwazr.class })
public class TaxonomyFacetTestSuite extends CommonTestSuite {

	public static class LuceneNoExecutor extends TaxonomyFacetLuceneTest {

		@BeforeClass
		public static void before() throws Exception {
			before(TestSettings.of(currentResults).executor(false).index(BaseTest.INDEX_NAME).settings());
		}
	}

	public static class LuceneWithExecutor extends TaxonomyFacetLuceneTest {

		@BeforeClass
		public static void before() throws Exception {
			before(TestSettings.of(currentResults).executor(true).index(BaseTest.INDEX_NAME).settings());
		}
	}

	static public class Qwazr extends TaxonomyFacetQwazrTest {

		@BeforeClass
		public static void before() throws Exception {
			before(TestSettings.of(currentResults).index(BaseTest.INDEX_NAME).settings());
		}
	}
}
