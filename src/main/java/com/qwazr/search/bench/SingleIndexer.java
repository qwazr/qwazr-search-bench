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
package com.qwazr.search.bench;

import org.apache.lucene.facet.FacetsConfig;

import java.util.function.BiConsumer;

/**
 * Created by ekeller on 15/02/2017.
 */
final public class SingleIndexer extends CommonIndexer {

	private final LuceneRecord.Indexable luceneRecord;

	public SingleIndexer(final LuceneCommonIndex luceneIndex, final FacetsConfig facetsConfig,
			final BiConsumer<TtlLineReader, LuceneRecord.Indexable> converter, final int batchSize) {
		super(luceneIndex, facetsConfig, converter, batchSize);
		luceneRecord = new LuceneRecord.Indexable();
	}

	@Override
	final public void accept(final TtlLineReader line) {
		index(line, luceneRecord);
	}

}
