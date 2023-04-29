package dk.sdu.student.mialb21.components;

import dk.sdu.student.mialb21.common.data.GameData;
import dk.sdu.student.mialb21.common.data.World;
import dk.sdu.student.mialb21.common.services.IPostEntityProcessingService;
import dk.sdu.student.mialb21.common.util.SPILocator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postProcessorInjector")
public class PostProcessInjection implements IProcessor {
    @Override
    public void process(GameData gameData, World world) {
        List<IPostEntityProcessingService> processors = SPILocator.locateAll(IPostEntityProcessingService.class);
        processors.forEach((processor) -> processor.process(gameData, world));
    }
}
