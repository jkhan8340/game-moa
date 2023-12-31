package com.game.moa.repository.jpa;

import com.game.moa.entity.Rune;
import com.game.moa.entity.RuneOption;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.game.moa.entity.RuneOption.ItemType.WEAPON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@ActiveProfiles("test")
@Sql("/data/rune-data-initialize.sql")
public class RuneRepositoryTest {

    @Autowired
    private RuneRepository runeRepository;

    @Test
    public void testFindAllByEnabledOrderByNumberAsc() {
        List<Rune> runeList = runeRepository.findDistinctByEnabledOrderByNumberAsc(true);
        assertThat(runeList.size()).isEqualTo(33);
        Rune rune = runeList.get(0);
        assertThat(rune.getRuneSeq()).isEqualTo(1L);
        assertThat(rune.getNumber()).isEqualTo(1);
        assertThat(rune.getName()).isEqualTo("엘룬");
        assertThat(rune.getEnglishName()).isEqualTo("El");
        assertThat(rune.getLevel()).isEqualTo(11);

        List<RuneOption> runeOptions = rune.getRuneOptions();
        assertThat(runeOptions.size()).isEqualTo(4);

        runeOptions.stream()
                .findFirst()
                .ifPresent((runeOption) -> {
                    assertThat(runeOption.getRuneOptionSeq()).isEqualTo(1L);
                    assertThat(runeOption.getAbility()).isEqualTo("+50 공격 등급, +1 시야");
                    assertThat(runeOption.getItemType()).isEqualTo(WEAPON);
                });
    }

}