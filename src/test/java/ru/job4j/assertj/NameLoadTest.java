package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParseNull() {
        NameLoad nameLoad = new NameLoad();
        String[] s = new String[]{};
        assertThatThrownBy(() -> nameLoad.parse(s))
                .isInstanceOf(IllegalArgumentException.class)
               .hasMessageContaining("is empty");
    }

    @Test
    void checkValidateIsExitSymb() {
        NameLoad nameLoad = new NameLoad();
        String[] s = new String[]{"key4-name4", "key1=name1", "key2=name2"};
        assertThatThrownBy(() -> nameLoad.parse(s))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("key4-name4")
                .hasMessageContaining("contain the symbol");
    }

    @Test
    void checkValidateIsNotBeginSymb() {
        NameLoad nameLoad = new NameLoad();
        String[] s = new String[]{"=key1name1", "key2=name2"};
        assertThatThrownBy(() -> nameLoad.parse(s))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=key1name1")
                .hasMessageContaining("contain a key");
    }

    @Test
    void checkValidateEndSymb() {
        NameLoad nameLoad = new NameLoad();
        String[] s = new String[]{"key1=name1", "key2=name2", "key3name3="};
        assertThatThrownBy(() -> nameLoad.parse(s))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("key3name3=")
                .hasMessageContaining("contain a value");
    }

}