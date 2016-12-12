package prv.mark.project.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prv.mark.project.common.entity.StockPriceEntity;
import prv.mark.project.common.repository.StockPriceRepository;
import prv.mark.project.common.service.StockPriceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Default implementation of the {@link StockPriceService} interface.
 *
 * @author mlglenn.
 */
@Service
public class StockPriceServiceImpl implements StockPriceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockPriceServiceImpl.class);

    @Value("#{systemProperties['ENVIRONMENT']}")
    private String env;

    @Autowired
    private StockPriceRepository stockPriceRepository;

    @Override
    public Optional<StockPriceEntity> findById(final Long id) {
        LOGGER.debug("StockPriceServiceImpl.findById({})", id);
        //return Optional.ofNullable(ordersRepository.findById(id)).get();
        return Optional.ofNullable(stockPriceRepository.findOne(id));
        //return Optional.of(new StockPrice());
    }

    @Override
    public Optional<StockPriceEntity> findByStockSymbol(final String stockSymbol) {
        LOGGER.debug("StockPriceServiceImpl.findByStockSymbol({})", stockSymbol);
        Optional<StockPriceEntity> stockPrice = stockPriceRepository.findByStockSymbol(stockSymbol);
        //Optional<StockPrice> stockPrice = Optional.of(new StockPrice());
        if (stockPrice.get() == null) {
            return null;
        }
        return stockPrice;
    }

    @Override
    public List<StockPriceEntity> findAll() {
        LOGGER.debug("StockPriceServiceImpl.findAll()");
        return stockPriceRepository.findAll();
        //return nullList();
    }

    @Override
    @Transactional
    public StockPriceEntity save(StockPriceEntity stockPrice) {
        LOGGER.debug("StockPriceServiceImpl.save({})", stockPrice.toString());
        return stockPriceRepository.saveAndFlush(stockPrice);
        //return new StockPrice();
    }

    private List<StockPriceEntity> nullList() {
        List<StockPriceEntity> list = new ArrayList<>();
        list.add(new StockPriceEntity());
        return list;
    }
}
