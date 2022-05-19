package com.itran.fgoc.common;

import com.github.weichun97.generate.autoconfigure.annotations.EnableGenerate;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author chun
 * @date 2022/5/19 10:07
 */
@EnableGenerate
@Configuration
@Profile("dev")
public class GenerateConfig {
}
