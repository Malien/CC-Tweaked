// SPDX-FileCopyrightText: 2023 The CC: Tweaked Developers
//
// SPDX-License-Identifier: MPL-2.0

package dan200.computercraft.data.recipe;

import dan200.computercraft.shared.recipe.RecipeProperties;
import dan200.computercraft.shared.recipe.ShapelessRecipeSpec;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

/**
 * A builder for {@link ShapelessRecipeSpec}s, much like {@link ShapelessRecipeBuilder}.
 */
public final class ShapelessSpecBuilder extends AbstractRecipeBuilder<ShapelessSpecBuilder, ShapelessRecipeSpec> {
    private final NonNullList<Ingredient> ingredients = NonNullList.create();

    public ShapelessSpecBuilder(HolderGetter<Item> items, RecipeCategory category, ItemStack result) {
        super(items, category, result);
    }

    public ShapelessSpecBuilder requires(Ingredient ingredient, int count) {
        for (var i = 0; i < count; i++) ingredients.add(ingredient);
        return this;
    }

    public ShapelessSpecBuilder requires(Ingredient ingredient) {
        return requires(ingredient, 1);
    }

    public ShapelessSpecBuilder requires(ItemLike item) {
        return requires(Ingredient.of(item));
    }

    public ShapelessSpecBuilder requires(ItemLike item, int count) {
        return requires(Ingredient.of(item), count);
    }

    public ShapelessSpecBuilder requires(TagKey<Item> item) {
        return requires(Ingredient.of(items.getOrThrow(item)));
    }

    @Override
    protected ShapelessRecipeSpec build(RecipeProperties properties) {
        return new ShapelessRecipeSpec(properties, ingredients, result);
    }

    public FinishedRecipe build() {
        return build(ShapelessRecipeSpec::create);
    }
}
