package saare_test

import saare.SaareMain
import saare_test.settings.Base

/**
 * Created by andriy on 14/07/15.
 */

public open class Main : SaareMain(Base) {
	companion object {
		@JvmStatic
		public fun main(args: Array<String>) {
			saare.SaareMain.main(args, Main::class.java as Class<SaareMain>);
		}
	}
}

