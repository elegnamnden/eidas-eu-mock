/*
 * Copyright (c) 2017 by European Commission
 *
 * Licensed under the EUPL, Version 1.1 or - as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * http://www.osor.eu/eupl/european-union-public-licence-eupl-v.1.1
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 *
 * This product combines work with different licenses. See the
 * "NOTICE" text file for details on the various modules and licenses.
 * The "NOTICE" text file is part of the distribution.
 * Any derivative works that you distribute must include a readable
 * copy of the "NOTICE" text file.
 */
package eu.eidas.engine.test.simple;

import eu.eidas.auth.engine.SamlEngineClock;
import org.joda.time.DateTime;

/**
 * A SamlEngineClock test implementation that can set its time relative to the system clock.
 */

public class SamlEngineTestClock implements SamlEngineClock {
    private long delta = 0;

    public DateTime getCurrentTime() {
        return new DateTime().plus(delta);
    }

    /**
     * Sets the delta time that this clock deviates from the system clock.
     *
     * @param deltaTime the delta time in milliseconds
     */
    public void setDelta(long deltaTime) {
        delta = deltaTime;
    }
}
