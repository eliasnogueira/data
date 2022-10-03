/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *  SPDX-License-Identifier: Apache-2.0
 */
package jakarta.data.repository;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PageableTest {

    @Test
    @DisplayName("Should correctly paginate")
    public void shouldCreatePageable() {
        Pageable pageable = Pageable.of(2, 6);

        assertSoftly(softly -> {
            softly.assertThat(pageable.getSize()).isEqualTo(6L);
            softly.assertThat(pageable.getPage()).isEqualTo(2L);
        });
    }

    @Test
    @DisplayName("Should navigate next")
    public void shouldNext() {
        Pageable pageable = Pageable.of(2, 1);
        Pageable next = pageable.next();

        assertSoftly(softly -> {
            softly.assertThat(pageable.getSize()).isEqualTo(1L);
            softly.assertThat(pageable.getPage()).isEqualTo(2L);
            softly.assertThat(next.getPage()).isEqualTo(3L);
            softly.assertThat(next.getSize()).isEqualTo(1L);
        });
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when page is not present")
    public void shouldReturnErrorWhenThereIsIllegalArgument() {
        assertThatIllegalArgumentException().isThrownBy(() -> Pageable.page(0));
        assertThatIllegalArgumentException().isThrownBy(() -> Pageable.page(-1));
        assertThatIllegalArgumentException().isThrownBy(() -> Pageable.of(1, -1));
        assertThatIllegalArgumentException().isThrownBy(() -> Pageable.of(1, 0));
    }
}