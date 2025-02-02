#loader contenttweaker

import mods.contenttweaker.VanillaFactory;

var compressed_air = VanillaFactory.createFluid("compressed_air", 0xafafaf);
compressed_air.density = 50;
compressed_air.gaseous = true;
compressed_air.temperature = 350;
compressed_air.viscosity = 1;
compressed_air.register();

var ash = VanillaFactory.createItem("ash");
ash.register();
