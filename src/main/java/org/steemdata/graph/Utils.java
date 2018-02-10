package org.steemdata.graph;

import eu.bittrade.libs.steemj.SteemJ;
import eu.bittrade.libs.steemj.base.models.AccountName;
import eu.bittrade.libs.steemj.configuration.SteemJConfig;
import eu.bittrade.libs.steemj.enums.PrivateKeyType;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseException;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pankaj Wahane on 11-02-2018.
 */
public class Utils {
    /**
     * Get the instance of SteemJ
     *
     * @param account
     * @param postingKey
     * @param activeKey
     * @return
     * @throws SteemCommunicationException
     * @throws SteemResponseException
     */
    public static SteemJ getSteemJInstance(String account, String postingKey, String activeKey) throws SteemCommunicationException, SteemResponseException {
        //Configuring the SteemJ instance
        final SteemJConfig myConfig = SteemJConfig.getInstance();
        myConfig.setResponseTimeout(100000);
        myConfig.setDefaultAccount(new AccountName(account));
        myConfig.setSteemJWeight((short) 0);
        // Add and manage private keys:
        final List<ImmutablePair<PrivateKeyType, String>> privateKeys = new ArrayList<>();
        privateKeys.add(new ImmutablePair<>(PrivateKeyType.POSTING, postingKey));
        privateKeys.add(new ImmutablePair<>(PrivateKeyType.ACTIVE, activeKey));
        myConfig.getPrivateKeyStorage().addAccount(myConfig.getDefaultAccount(), privateKeys);
        // Create a new apiWrapper with config object.
        final SteemJ steemJ = new SteemJ();
        return steemJ;
    }
}
