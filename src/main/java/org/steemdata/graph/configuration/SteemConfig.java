package org.steemdata.graph.configuration;

import eu.bittrade.libs.steemj.SteemJ;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.steemdata.graph.Utils;

/**
 * Created by Pankaj Wahane on 11-02-2018.
 */
@Configuration
@Slf4j
public class SteemConfig {

    @Value("${steemit.account.name}")
    String accountName;

    @Value("${steemit.account.postkey}")
    String postKey;

    @Value("${steemit.account.activekey}")
    String activeKey;

    @Bean
    SteemJ steemj() throws SteemCommunicationException, SteemResponseException {
        log.info(String.format("account(%s), postKey(%s), activeKey(%s)", accountName, postKey, activeKey));
        return Utils.getSteemJInstance(accountName, postKey, activeKey);
    }
}
