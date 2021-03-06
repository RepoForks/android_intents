/*
 * =================================================================================================
 *                             Copyright (C) 2016 Universum Studios
 * =================================================================================================
 *         Licensed under the Apache License, Version 2.0 or later (further "License" only).
 * -------------------------------------------------------------------------------------------------
 * You may use this file only in compliance with the License. More details and copy of this License 
 * you may obtain at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * You can redistribute, modify or publish any part of the code written within this file but as it 
 * is described in the License, the software distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES or CONDITIONS OF ANY KIND.
 * 
 * See the License for the specific language governing permissions and limitations under the License.
 * =================================================================================================
 */
package universum.studios.android.intent;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Factory that may be used to create instances of {@link IntentStarter} for a desired intent context.
 * <p>
 * Below are listed factory methods that provide standard intent starters:
 * <ul>
 * <li>{@link #activityStarter(Activity)}</li>
 * <li>{@link #fragmentStarter(Fragment)}</li>
 * <li>{@link #supportFragmentStarter(android.support.v4.app.Fragment)}</li>
 * </ul>
 *
 * @author Martin Albedinsky
 */
public final class IntentStarters {

	/**
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = "IntentStarters";

	/**
	 * Interface ===================================================================================
	 */

	/**
	 * Static members ==============================================================================
	 */

	/**
	 * Members =====================================================================================
	 */

	/**
	 * Constructors ================================================================================
	 */

	/**
	 */
	private IntentStarters() {
		// Creation of instances of this class is not publicly allowed.
	}

	/**
	 * Methods =====================================================================================
	 */

	/**
	 * Creates an instance of IntentStarter for the given <var>activity</var> context.
	 *
	 * @param activity The activity for which to create intent starter.
	 * @return IntentStarter ready to start the desired intent via {@link BaseIntent#startWith(IntentStarter)}.
	 */
	@NonNull
	public static IntentStarter activityStarter(@NonNull Activity activity) {
		return new ActivityStarter(activity);
	}

	/**
	 * Creates an instance of IntentStarter for the given <var>fragment</var> context.
	 *
	 * @param fragment The fragment for which to create intent starter.
	 * @return IntentStarter ready to start the desired intent via {@link BaseIntent#startWith(IntentStarter)}.
	 */
	public static IntentStarter fragmentStarter(@NonNull Fragment fragment) {
		return new FragmentStarter(fragment);
	}

	/**
	 * Creates an instance of IntentStarter for the given support <var>fragment</var> context.
	 *
	 * @param fragment The fragment for which to create intent starter.
	 * @return IntentStarter ready to start the desired intent via {@link BaseIntent#startWith(IntentStarter)}.
	 */
	public static IntentStarter supportFragmentStarter(@NonNull android.support.v4.app.Fragment fragment) {
		return new SupportFragmentStarter(fragment);
	}

	/**
	 * Inner classes ===============================================================================
	 */

	/**
	 * A {@link IntentStarter} implementation for {@link Activity} context.
	 */
	private static final class ActivityStarter implements IntentStarter {

		/**
		 * The activity to which to delegate intent starting.
		 */
		private final Activity activity;

		/**
		 * Creates a new ActivityStarter for the given <var>activity</var>.
		 *
		 * @param activity The activity to which to delegate intent starting.
		 */
		private ActivityStarter(Activity activity) {
			this.activity = activity;
		}

		/**
		 */
		@NonNull
		@Override
		public Context getContext() {
			return activity;
		}

		/**
		 */
		@Override
		public void startIntent(@NonNull Intent intent) {
			activity.startActivity(intent);
		}

		/**
		 */
		@Override
		public void startIntent(@NonNull Intent intent, @Nullable Bundle options) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				activity.startActivity(intent, options);
			}
		}

		/**
		 */
		@Override
		public void startIntentForResult(@NonNull Intent intent, int requestCode) {
			activity.startActivityForResult(intent, requestCode);
		}

		/**
		 */
		@Override
		public void startIntentForResult(@NonNull Intent intent, int requestCode, @Nullable Bundle options) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				activity.startActivityForResult(intent, requestCode, options);
			}
		}

		/**
		 */
		@Override
		public void overridePendingTransition(@AnimRes int enterAnimRes, @AnimRes int exitAnimRes) {
			activity.overridePendingTransition(enterAnimRes, exitAnimRes);
		}
	}

	/**
	 * A {@link IntentStarter} implementation for {@link Fragment} context.
	 */
	private static final class FragmentStarter implements IntentStarter {

		/**
		 * The fragment to which to delegate intent starting.
		 */
		private final Fragment fragment;

		/**
		 * Creates a new FragmentStarter for the given <var>fragment</var>.
		 *
		 * @param fragment The fragment to which to delegate intent starting.
		 */
		private FragmentStarter(Fragment fragment) {
			this.fragment = fragment;
		}

		/**
		 */
		@NonNull
		@Override
		public Context getContext() {
			return fragment.getActivity();
		}

		/**
		 */
		@Override
		public void startIntent(@NonNull Intent intent) {
			fragment.startActivity(intent);
		}

		/**
		 */
		@Override
		public void startIntent(@NonNull Intent intent, @Nullable Bundle options) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				fragment.startActivity(intent, options);
			}
		}

		/**
		 */
		@Override
		public void startIntentForResult(@NonNull Intent intent, int requestCode) {
			fragment.startActivityForResult(intent, requestCode);
		}

		/**
		 */
		@Override
		public void startIntentForResult(@NonNull Intent intent, int requestCode, @Nullable Bundle options) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				fragment.startActivityForResult(intent, requestCode, options);
			}
		}

		/**
		 */
		@Override
		public void overridePendingTransition(@AnimRes int enterAnimRes, @AnimRes int exitAnimRes) {
			fragment.getActivity().overridePendingTransition(enterAnimRes, exitAnimRes);
		}
	}

	/**
	 *  A {@link IntentStarter} implementation for support {@link android.support.v4.app.Fragment Fragment}
	 *  context.
	 */
	private static final class SupportFragmentStarter implements IntentStarter {

		/**
		 * The fragment to which to delegate intent starting.
		 */
		private final android.support.v4.app.Fragment fragment;

		/**
		 * Creates a new FragmentStarter for the given <var>fragment</var>.
		 *
		 * @param fragment The fragment to which to delegate intent starting.
		 */
		private SupportFragmentStarter(android.support.v4.app.Fragment fragment) {
			this.fragment = fragment;
		}

		/**
		 */
		@NonNull
		@Override
		public Context getContext() {
			return fragment.getActivity();
		}

		/**
		 */
		@Override
		public void startIntent(@NonNull Intent intent) {
			fragment.startActivity(intent);
		}

		/**
		 */
		@Override
		public void startIntent(@NonNull Intent intent, @Nullable Bundle options) {
			fragment.startActivity(intent, options);
		}

		/**
		 */
		@Override
		public void startIntentForResult(@NonNull Intent intent, int requestCode) {
			fragment.startActivityForResult(intent, requestCode);
		}

		/**
		 */
		@Override
		public void startIntentForResult(@NonNull Intent intent, int requestCode, @Nullable Bundle options) {
			fragment.startActivityForResult(intent, requestCode, options);
		}

		/**
		 */
		@Override
		public void overridePendingTransition(@AnimRes int enterAnimRes, @AnimRes int exitAnimRes) {
			fragment.getActivity().overridePendingTransition(enterAnimRes, exitAnimRes);
		}
	}
}
