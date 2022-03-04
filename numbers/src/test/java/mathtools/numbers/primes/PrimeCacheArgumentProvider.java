package mathtools.numbers.primes;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

/** ArgumentProvider for all [PrimeCacheBase] implementations
 * @author DK96-OS : 2022 */
public final class PrimeCacheArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(
            ExtensionContext context
    ) throws Exception {
        return Stream.of(
                new BytePrimeCache(),
                new ShortPrimeCache()
        ).map(Arguments::of);
    }

}
