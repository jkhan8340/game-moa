package com.game.moa.service.rune;

import com.game.moa.repository.jpa.RuneRepository;
import com.game.moa.vo.RuneOptionVO;
import com.game.moa.vo.RuneVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RuneServiceImpl implements RuneService {

    private final RuneRepository runeRepository;

    public RuneServiceImpl(RuneRepository runeRepository) {
        this.runeRepository = runeRepository;
    }

    @Override
    public List<RuneVO> getRuneList() {
        return runeRepository.findByEnabledOrderByNumberAsc(true)
                .stream()
                .map(rune -> RuneVO.builder()
                        .level(rune.getLevel())
                        .englishName(rune.getEnglishName())
                        .name(rune.getName())
                        .number(rune.getNumber())
                        .runeOptions(rune.getRuneOptions().stream()
                                .map(runeOption -> RuneOptionVO.builder()
                                        .ability(runeOption.getAbility())
                                        .itemType(runeOption.getItemType())
                                        .build())
                                .collect(Collectors.toSet()))
                        .build())
                .toList();
    }
}
