package org.steemdata.graph.configuration;

import eu.bittrade.libs.steemj.SteemJ;
import eu.bittrade.libs.steemj.base.models.AccountName;
import eu.bittrade.libs.steemj.configuration.SteemJConfig;
import eu.bittrade.libs.steemj.enums.PrivateKeyType;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

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

        SteemJConfig steemJConfig = SteemJConfig.getInstance();
        steemJConfig.setResponseTimeout(100000);
        steemJConfig.setDefaultAccount(new AccountName(accountName));

        List<ImmutablePair<PrivateKeyType, String>> privateKeys = new ArrayList<>();
        privateKeys.add(new ImmutablePair<>(PrivateKeyType.POSTING, postKey));
        privateKeys.add(new ImmutablePair<>(PrivateKeyType.ACTIVE, activeKey));

        steemJConfig.getPrivateKeyStorage().addAccount(steemJConfig.getDefaultAccount(), privateKeys);

        SteemJ steemJ = new SteemJ();
        return steemJ;
    }
}
