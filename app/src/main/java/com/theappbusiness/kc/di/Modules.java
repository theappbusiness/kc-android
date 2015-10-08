/*
 * Copyright (C) 2015 The App Business.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.theappbusiness.kc.di;

import com.theappbusiness.kc.KcApp;

/**
 * Created by Nabil Hachicha on 06/12/14.
 */
public final class Modules {
    public static Object[] list(KcApp app) {
        return new Object[] {
                new KcModule(app)
        };
    }

    private Modules() {
        // No instances.
    }
}