/*
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

import com.qwazr.search.bench.LuceneRecord;
import com.qwazr.search.bench.TtlLineReader;
import com.qwazr.search.bench.test.LuceneTest;
import com.qwazr.search.bench.test.TestSettings;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.facet.FacetField;
import org.apache.lucene.facet.FacetsConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.util.BytesRef;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class TaxonomyFacetLuceneTest extends LuceneTest {

	final static String URL = "url";
	final static String PREDICATE = "predicate";

	final static FacetsConfig FACETS_CONFIG = new FacetsConfig();

	static {
		FACETS_CONFIG.setMultiValued(PREDICATE, true);
	}

	@Parameterized.Parameters
	public static Collection<Boolean> iterations() {
		return Arrays.asList(true, false);
	}

	public static void before(final TestSettings.Builder settingsBuilder) throws Exception {
		LuceneTest.before(settingsBuilder.index(0).taxonomy(true).settings());
	}

	@Override
	final public void accept(final TtlLineReader lineReader, final LuceneRecord.Indexable record) {
		final BytesRef termBytesRef = new BytesRef(lineReader.subject);
		record.reset(new Term(URL, termBytesRef));
		record.document.add(new StringField(URL, termBytesRef, Field.Store.NO));
		record.document.add(new FacetField(PREDICATE, lineReader.predicate));
	}

}
