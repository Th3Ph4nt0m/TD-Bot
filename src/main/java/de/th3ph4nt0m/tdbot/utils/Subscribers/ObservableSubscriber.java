/*******************************************************************************
 ObservableSubscriber.java is part of the TD-Bot project

 TD-Bot is the Discord-Bot of the TD-Nation Discord Server.
 Copyright (C) 2020 Henrik Steffens

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published
 by the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <https://www.gnu.org/licenses/>.

 Last edit: 2020/11/1
 ******************************************************************************/
package de.th3ph4nt0m.tdbot.utils.Subscribers;

import com.mongodb.MongoTimeoutException;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * A Subscriber that stores the publishers results and provides a latch so can block on completion.
 *
 * @param <T> The publishers result type
 */
public  class ObservableSubscriber<T> implements Subscriber<T> {
	private final List<T> received;
	private final List<Throwable> errors;
	private final CountDownLatch latch;
	private volatile Subscription subscription;
	private volatile boolean completed;

	public ObservableSubscriber() {
		this.received = new ArrayList<>();
		this.errors = new ArrayList<>();
		this.latch = new CountDownLatch(1);
	}

	@Override
	public void onSubscribe(final Subscription s) {
		subscription = s;
	}

	@Override
	public void onNext(final T t) {
		received.add(t);
	}

	@Override
	public void onError(final Throwable t) {
		errors.add(t);
		onComplete();
	}

	@Override
	public void onComplete() {
		completed = true;
		latch.countDown();
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public List<T> getReceived() {
		return received;
	}

	public Throwable getError() {
		if (errors.size() > 0) {
			return errors.get(0);
		}
		return null;
	}

	public boolean isCompleted() {
		return completed;
	}

	public List<T> get(final long timeout, final TimeUnit unit) throws Throwable {
		return await(timeout, unit).getReceived();
	}

	public ObservableSubscriber<T> await() throws Throwable {
		return await(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
	}

	public ObservableSubscriber<T> await(final long timeout, final TimeUnit unit) throws Throwable {
		subscription.request(Integer.MAX_VALUE);
		if (!latch.await(timeout, unit)) {
			throw new MongoTimeoutException("Publisher onComplete timed out");
		}
		if (!errors.isEmpty()) {
			throw errors.get(0);
		}
		return this;
	}
}
